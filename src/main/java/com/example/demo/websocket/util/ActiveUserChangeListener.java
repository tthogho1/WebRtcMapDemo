package com.example.demo.websocket.util;

/**
 * This interface used as an Observer for ActiveUserManager class
 */
public interface ActiveUserChangeListener {

	/**
	 * call when Observable's internal state is changed.
	 */
	void notifyActiveUserChange();
}