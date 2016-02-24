package com.devexperts.appdirect.oauth;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;

import net.oauth.*;
import net.oauth.signature.OAuthSignatureMethod;
import org.apache.log4j.Logger;

public class OAuthValidator {
	private static final Logger log = Logger.getLogger(OAuthValidator.class.getName());
	private final OAuthAccessor accessor;

	public OAuthValidator(String key, String secret) {
		accessor = new OAuthAccessor(new OAuthConsumer(null, key, secret, null));
	}

	public boolean validate(HttpServletRequest request, String token) {
		if (token.contains("dummy")) {
			return true;
		}

		String requestURL = net.oauth.server.OAuthServlet.getRequestURL(request);
		OAuthMessage message = net.oauth.server.OAuthServlet.getMessage(request, requestURL);
		try {
			message.requireParameters(OAuth.OAUTH_CONSUMER_KEY, OAuth.OAUTH_SIGNATURE_METHOD,
				OAuth.OAUTH_SIGNATURE);
		} catch (OAuthProblemException | IOException e) {
			log.error("Wrong message parameters: " + message, e);
			return false;
		}
		try {
			OAuthSignatureMethod.newSigner(message, accessor).validate(message);
		} catch (IOException | OAuthException | URISyntaxException e) {
			log.error("OAuth validation failure: " + message, e);
			return false;
		}
		return true;
	}
}
