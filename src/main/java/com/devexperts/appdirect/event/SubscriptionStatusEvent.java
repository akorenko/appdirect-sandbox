package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class SubscriptionStatusEvent extends Event {
	private SubscriptionStatusPayload payload;

	public SubscriptionStatusEvent() {
		super(EventType.SUBSCRIPTION_STATUS);
	}
}
