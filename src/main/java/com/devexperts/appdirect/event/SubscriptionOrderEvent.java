package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class SubscriptionOrderEvent extends Event {

	private SubscriptionOrderPayload payload;

	public SubscriptionOrderEvent() {
		super(EventType.SUBSCRIPTION_ORDER);
	}
}
