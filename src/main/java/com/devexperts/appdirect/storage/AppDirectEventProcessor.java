package com.devexperts.appdirect.storage;

import com.devexperts.appdirect.event.*;
import com.devexperts.appdirect.to.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppDirectEventProcessor {
	private static final Logger log = Logger.getLogger(AppDirectEventProcessor.class);
	@Autowired
	private UserStorage userStorage;

	public synchronized void onEvent(SubscriptionOrderEvent event) {
		UserData user = userStorage.findByOpenId(event.getCreator().getOpenId());
		if (user != null) {
			user.setContent(event.toString());
			userStorage.update(user);
		} else {
			UserData newUser = new UserData();
			newUser.setOpenId(event.getCreator().getOpenId());
			newUser.setCompany(event.getPayload().getCompany().getName());
			newUser.setFirstName(event.getCreator().getFirstName());
			newUser.setLastName(event.getCreator().getLastName());
			newUser.setContent(event.toString());
			userStorage.add(newUser);
		}
	}

	public synchronized void onEvent(SubscriptionStatusEvent event) {
		//nothing to do here
	}


	public synchronized void onEvent(SubscriptionChangeEvent event) {
		UserData user = userStorage.findByOpenId(event.getCreator().getOpenId());
		if (user != null) {
			user.setOpenId(event.getCreator().getOpenId());
			user.setFirstName(event.getCreator().getFirstName());
			user.setLastName(event.getCreator().getLastName());
			user.setContent(event.toString());
			userStorage.update(user);
		} else {
			log.warn("Can't find user with openId: " + event.getCreator().getOpenId());
		}
	}

	public synchronized void onEvent(SubscriptionCancelEvent event) {
		UserData toRemove = userStorage.findByOpenId(event.getCreator().getOpenId());
		if (toRemove != null) {
			userStorage.removeProfile(toRemove.getId());
		}
	}

	public synchronized void onEvent(UserAssignedEvent event) {
		UserData user = userStorage.findByOpenId(event.getPayload().getUser().getOpenId());
		if (user != null) {
			user.setContent(event.toString());
			userStorage.update(user);
		} else {
			UserData userData = new UserData();
			userData.setOpenId(event.getPayload().getUser().getOpenId());
			userData.setFirstName(event.getPayload().getUser().getFirstName());
			userData.setLastName(event.getPayload().getUser().getLastName());
			userData.setContent(event.toString());
			userStorage.add(userData);
		}
	}

	public synchronized void onEvent(UserUnassignedEvent event) {
		UserData userDataToRemoved = userStorage.findByOpenId(event.getPayload().getUser().getOpenId());
		if (userDataToRemoved != null) {
			userStorage.removeProfile(userDataToRemoved.getId());
		}
	}
}
