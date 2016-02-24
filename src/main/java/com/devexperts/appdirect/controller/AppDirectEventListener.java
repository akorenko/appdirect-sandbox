package com.devexperts.appdirect.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import com.devexperts.appdirect.event.*;
import com.devexperts.appdirect.oauth.*;
import com.devexperts.appdirect.storage.AppDirectEventProcessor;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppDirectEventListener {
	@Autowired
	private AppDirectEventProcessor appDirectEventProcessor;
	@Autowired
	private OAuthValidator oAuthValidator;
	@Autowired
	private OAuthSigner oAuthSigner;

	private final HttpClient httpClient = HttpClientBuilder.create().build();

	@RequestMapping("/subscription/order")
	public EventResult orderSubscription(HttpServletRequest request, @RequestParam String endpointUrl) {
		try {
			SubscriptionOrderEvent event = processEvent(request, endpointUrl, SubscriptionOrderEvent.class);
			appDirectEventProcessor.onEvent(event);
			return buildResult(true, event.toString());
		} catch (Exception e) {
			return buildResult(false, e.getMessage());
		}
	}

	@RequestMapping("/subscription/change")
	public EventResult changeSubscription(HttpServletRequest request, @RequestParam String endpointUrl) {
		try {
			SubscriptionChangeEvent event = processEvent(request, endpointUrl, SubscriptionChangeEvent.class);
			appDirectEventProcessor.onEvent(event);
			return buildResult(true, event.toString());
		} catch (Exception e) {
			return buildResult(false, e.getMessage());
		}
	}

	@RequestMapping("/subscription/status")
	public EventResult statusSubscription(HttpServletRequest request, @RequestParam String endpointUrl) {
		try {
			SubscriptionStatusEvent event = processEvent(request, endpointUrl, SubscriptionStatusEvent.class);
			appDirectEventProcessor.onEvent(event);
			return buildResult(true, event.toString());
		} catch (Exception e) {
			return buildResult(false, e.getMessage());
		}
	}

	@RequestMapping("/user/assign")
	public EventResult assignUser(HttpServletRequest request, @RequestParam String endpointUrl) {
		try {
			UserAssignedEvent event = processEvent(request, endpointUrl, UserAssignedEvent.class);
			appDirectEventProcessor.onEvent(event);
			return buildResult(true, event.toString());
		} catch (Exception e) {
			return buildResult(false, e.getMessage());
		}
	}

	@RequestMapping("/user/unassign")
	public EventResult unassignUser(HttpServletRequest request, @RequestParam String endpointUrl) {
		try {
			UserUnassignedEvent event = processEvent(request, endpointUrl, UserUnassignedEvent.class);
			appDirectEventProcessor.onEvent(event);
			return buildResult(true, event.toString());
		} catch (Exception e) {
			return buildResult(false, e.getMessage());
		}
	}

	@RequestMapping("/subscription/cancel")
	public EventResult cancelSubscription(HttpServletRequest request, @RequestParam String endpointUrl) {
		try {
			SubscriptionCancelEvent event =	processEvent(request, endpointUrl, SubscriptionCancelEvent.class);
			appDirectEventProcessor.onEvent(event);
			return buildResult(true, event.toString());
		} catch (Exception e) {
			return buildResult(false, e.getMessage());
		}
	}

	public <T> T processEvent(HttpServletRequest request, String endpointUrl, Class<T> clazz) throws IOException, JAXBException {
		boolean valid = oAuthValidator.validate(request, endpointUrl);
		if (!valid)
			throw new OAuthOperationException("Not valid request");

		String signedEndpointUrl = oAuthSigner.sign(endpointUrl);
		if (signedEndpointUrl == null)
			throw new OAuthOperationException("Couldn't sign url: " + endpointUrl);


		HttpGet getRequest = new HttpGet(signedEndpointUrl);
		HttpResponse response = httpClient.execute(getRequest);
		return parseResponse(response.getEntity().getContent(), clazz);
	}

	private EventResult buildResult(boolean success, String message) {
		EventResult result = new EventResult();
		result.setSuccess(success);
		result.setMessage(message);
		return result;
	}

	protected <T> T parseResponse(final InputStream content, final Class<T> resultType) throws JAXBException, IOException {
		if (String.class.isAssignableFrom(resultType)) {
			return (T)IOUtils.toString(content);
		}
		if (resultType.isAnnotationPresent(XmlRootElement.class)) {
			return (T)JAXBContext.newInstance(resultType).createUnmarshaller().unmarshal(content);
		}
		return null;
	}
}
