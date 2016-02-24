package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement(name = "payload")
public class SubscriptionStatusPayload {
	Account account;
	Notice notice;
}
