package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class UserUnassignedEvent extends Event {

	private AssignmentPayload payload;

	public UserUnassignedEvent() {
		super(EventType.USER_UNASSIGNED);
	}
}