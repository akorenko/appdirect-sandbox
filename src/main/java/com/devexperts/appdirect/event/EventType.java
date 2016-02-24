package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	SUBSCRIPTION_ORDER,
	SUBSCRIPTION_CHANGE,
	SUBSCRIPTION_STATUS,
	SUBSCRIPTION_CANCEL,
	USER_ASSIGNED,
	USER_UNASSIGNED
}
