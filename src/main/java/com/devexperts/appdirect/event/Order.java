package com.devexperts.appdirect.event;

import java.util.List;
import javax.xml.bind.annotation.*;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

	@XmlElement
	private String editionCode;

	@XmlElement(name = "item")
	private List<OrderItem> orderItem;

}
