package com.devexperts.appdirect.storage;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.devexperts.appdirect.to.UserData;
import org.springframework.stereotype.Service;

@Service
public class UserStorage {

	private final ConcurrentHashMap<Integer, UserData> users = new ConcurrentHashMap<>();
	private final AtomicInteger curId = new AtomicInteger();

	public void add(UserData userData) {
		if (userData.getId() == -1)
			userData.setId(curId.getAndIncrement());
		users.put(userData.getId(), userData);
	}

	public void update(UserData userData) {
		add(userData);
	}

	public Collection<UserData> list() {
		return users.values();
	}

	public void removeProfile(Integer id) {
		users.remove(id);
	}

	public UserData findById(Integer id) {
		return users.get(id);
	}

	public UserData findByOpenId(String openId) {
		for (UserData userData : users.values()) {
			if (userData.getOpenId().equals(openId)) {
				return userData;
			}
		}
		return null;
	}

}
