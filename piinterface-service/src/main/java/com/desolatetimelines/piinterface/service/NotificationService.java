package com.desolatetimelines.piinterface.service;

import com.desolatetimelines.piinterface.service.model.Notification;

public interface NotificationService {
	/**
	 * Add a notification to the queue
	 */
	void queueNotification(Notification notification);

	/**
	 * Reads X messages from the queue without removing them
	 */
	Iterable<Notification> peekQueue(int nMessages);

	/**
	 * Reads one element from the head of the queue without removing any messages
	 */
	Notification peekQueue();

	/**
	 * Retrieves the entire queue without removing any messages
	 */
	Iterable<Notification> peekEntireQueue();

	/**
	 * Retrieves the last element in the queue
	 */
	Notification peekLast();

	/**
	 * Retrieves the last N elements in the queue
	 */
	Iterable<Notification> peekLast(int nMessages); 

	/**
	 * Empties the queue
	 */;
	void clear();

	/**
	 * Saves the state so that it may be loaded later
	 */
	void persist();

	/**
	 * Loads the persisted state
	 */
	void load();
}
