package com.devexperts.appdirect.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Event {
	private EventType type;
	private Marketplace marketplace;
	private User creator;

	public Event(EventType type) {
		this.type = type;
	}
}