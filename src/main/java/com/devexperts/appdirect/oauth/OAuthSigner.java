package com.devexperts.appdirect.oauth;


import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.*;
import org.apache.log4j.Logger;

public class OAuthSigner {
	private static final Logger log = Logger.getLogger(OAuthSigner.class);
	private final OAuthConsumer consumer;

	public OAuthSigner(String key, String secret) {
		this.consumer = new DefaultOAuthConsumer(key, secret);
	}

	public String sign(String url) {
		try {
			return consumer.sign(url);
		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
			log.error("Couldn't sign url: " + url, e);
		}
		return null;
	}
}
