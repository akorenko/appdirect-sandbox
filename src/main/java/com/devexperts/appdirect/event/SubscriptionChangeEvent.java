package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class SubscriptionChangeEvent extends Event {
	private SubscriptionChangePayload payload;

	public SubscriptionChangeEvent() {
		super(EventType.SUBSCRIPTION_CHANGE);
	}
}
