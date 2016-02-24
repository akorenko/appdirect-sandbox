package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class SubscriptionCancelEvent extends Event {
	private SubscriptionCancelPayload payload;

	public SubscriptionCancelEvent() {
		super(EventType.SUBSCRIPTION_CANCEL);
	}
}
