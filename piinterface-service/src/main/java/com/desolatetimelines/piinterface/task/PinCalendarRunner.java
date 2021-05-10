package com.desolatetimelines.piinterface.task;

import static com.desolatetimelines.piinterface.service.Constants.PIN_CALENDAR_ACTION_ON;
import static com.desolatetimelines.piinterface.service.Constants.PIN_CALENDAR_ACTION_OFF;
import static com.desolatetimelines.piinterface.service.Constants.PIN_CALENDAR_ACTION_OFF_ON;
import static com.desolatetimelines.piinterface.service.Constants.PIN_CALENDAR_ACTION_ON_OFF;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desolatetimelines.piinterface.model.PinCalendarEntry;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@Component
public class PinCalendarRunner {
	private static int TIME_INTERVAL_ANY;

	@Autowired
	PiInterfaceService piInterface;

	@Scheduled(fixedRate = 60000)
	public void run() {
		// Get the current date and time
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		int minute = now.getMinute();

		// Get the calendar
		Iterable<PinCalendarEntry> calendarEntries
			= piInterface.getDataService().getPinCalendarEntriesRepository().findAll();

		// Parse the calendar
		if (calendarEntries != null) {
			calendarEntries.forEach(entry -> {
				if (calendarEntryIsNow(entry, year, month, day, hour, minute)) {
					processPinCalendarEntry(entry);
				}
			});
		}
	}

	private void processPinCalendarEntry(PinCalendarEntry entry) {
		if (entry.getAction().getName().equals(PIN_CALENDAR_ACTION_OFF)) {
			piInterface.setPinButtonState(entry.getPin(), 0);
			return;
		}

		if (entry.getAction().getName().equals(PIN_CALENDAR_ACTION_ON)) {
			piInterface.setPinButtonState(entry.getPin(), 1);
			return;
		}

		if (entry.getAction().getName().equals(PIN_CALENDAR_ACTION_ON_OFF)) {
			int delayMs = (entry.getPin().getDelayMs() == null ? 300 : entry.getPin().getDelayMs());

			piInterface.setPinButtonState(entry.getPin(), 1);

			try {
				Thread.sleep(delayMs);
			} catch (InterruptedException e) {
			}

			piInterface.setPinButtonState(entry.getPin(), 0);
			return;
		}

		if (entry.getAction().getName().equals(PIN_CALENDAR_ACTION_OFF_ON)) {
			int delayMs = (entry.getPin().getDelayMs() == null ? 300 : entry.getPin().getDelayMs());

			piInterface.setPinButtonState(entry.getPin(), 0);

			try {
				Thread.sleep(delayMs);
			} catch (InterruptedException e) {
			}

			piInterface.setPinButtonState(entry.getPin(), 1);
			return;
		}
	}

	private static boolean calendarEntryIsNow(PinCalendarEntry entry, int year, int month, int day, int hour, int minute) {
		return
			   (entry.getYear()   == TIME_INTERVAL_ANY || entry.getYear()   == year)
			&& (entry.getMonth()  == TIME_INTERVAL_ANY || entry.getMonth()  == month)
			&& (entry.getDay()    == TIME_INTERVAL_ANY || entry.getDay()    == day)
			&& (entry.getHour()   == TIME_INTERVAL_ANY || entry.getHour()   == hour)
			&& (entry.getMinute() == TIME_INTERVAL_ANY || entry.getMinute() == minute)
		;
	}
}
