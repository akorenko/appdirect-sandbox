package com.devexperts.appdirect.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AssignmentPayload {
	Account account;
	User user;
}