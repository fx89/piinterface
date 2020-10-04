package com.desolatetimelines.piinterface.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.desolatetimelines.piinterface.service.model.Notification;

/**
 * Simple array-backed queue with the added ability to peek the tail
 */
@Component
public class ConcurrentArrayBackedNotificationServiceImpl implements NotificationService {

	private int maxMessagesPerQueue = 20;

	private Notification[] notifications = new Notification[maxMessagesPerQueue];
	private int nMessages = 0;

	private boolean working = false;

	private void waitForOtherThreads() {
		while (working) {
			
		}
	}

	private void resizeNotificationsArray(int newSize) {
		working = true;

		Notification[] newNotificationsArray = new Notification[newSize];
		for (int n = 0; n < nMessages; n++) {
			newNotificationsArray[n] = notifications[n];
		}

		notifications = newNotificationsArray;

		working = false;
	}

	private void shiftNotificationsArray() {
		for (int n = 0; n < nMessages - 1; n++) {
			notifications[n] = notifications[n + 1];
		}

		if (nMessages > 0) {
			nMessages--;
		}
	}

	public void setMaxMessagesPerQueue(int maxMessagesPerQueue) {
		this.maxMessagesPerQueue = maxMessagesPerQueue;
		resizeNotificationsArray(maxMessagesPerQueue);
	}

	/**
	 * If, after adding the message, the queue size exceeds the maximum allowed
	 * size, then the head message is popped from the queue.
	 */
	@Override
	public void queueNotification(Notification notification) {
		waitForOtherThreads();

		working = true;

		if (nMessages >= maxMessagesPerQueue) {
			shiftNotificationsArray();
		}

		notifications[nMessages] = notification;
		nMessages++;

		working = false;
	}

	@Override
	public Iterable<Notification> peekQueue(int nMessages) {
		waitForOtherThreads();

		if (nMessages == 0) {
			return null;
		}

		working = true;

		int max = nMessages > this.nMessages ? this.nMessages : nMessages;

		List<Notification> ret = new ArrayList<>();

		for (int n = 0; n < max; n++) {
			ret.add(notifications[n]);
		}

		working = false;

		return ret;
	}

	@Override
	public Notification peekQueue() {
		waitForOtherThreads();

		if (nMessages > 0) {
			return notifications[0];
		} else {
			return null;
		}
	}

	@Override
	public Iterable<Notification> peekEntireQueue() {
		return peekQueue(nMessages);
	}

	@Override
	public Notification peekLast() {
		waitForOtherThreads();

		if (nMessages > 0) {
			return notifications[nMessages - 1];
		} else {
			return null;
		}
	}

	@Override
	public Iterable<Notification> peekLast(int nMessages) {
		waitForOtherThreads();

		working = true;

		if (nMessages == 0) {
			working = false;
			return null;
		}

		int max = nMessages > this.nMessages ? this.nMessages : nMessages;

		List<Notification> ret = new ArrayList<>();

		for (int n = max-1; n >= 0; n--) {
			ret.add(notifications[n]);
		}

		working = false;

		return ret;
	}

	public void clear() {
		this.nMessages = 0;
	}

	/**
	 * This is not implemented here. It will simply do nothing.
	 */
	@Override
	public void persist() {
	}

	/**
	 * This is not implemented here. It will simply do nothing.
	 */
	@Override
	public void load() {
	}

}
