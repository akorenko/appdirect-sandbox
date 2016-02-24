package com.devexperts.appdirect.event;


import javax.xml.bind.annotation.*;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement(name = "result")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EventResult {
	private boolean success=true;
	private String message;
	private String errorCode;
	private String accountIdentifier;
}
