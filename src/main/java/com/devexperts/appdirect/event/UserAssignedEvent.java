package com.devexperts.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class UserAssignedEvent extends Event {

	private AssignmentPayload payload;

	public UserAssignedEvent() {
		super(EventType.USER_ASSIGNED);
	}
}
