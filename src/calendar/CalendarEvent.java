package calendar;

import java.util.*;

public class CalendarEvent {
	private String title;
	private Date dueDate;
	private String description;
	private String subject;
	private Date notificationDate; // TODO: use Twilio API to send to users
	
	/**
	 * Creates a new <code>CalendarEvent</code>, which holds information for
	 * a pending date of an assignment item.
	 * @param title A short, descriptive title of the event.
	 * @param due The due date of the assignment.
	 * @param descr A more verbose description of the event.
	 * @param subj A predetermined scholarly subject of the event.
	 */
	public CalendarEvent(String title, Date due, String descr, String subj) {
		this.title = title;
		dueDate = due;
		description = descr;
		subject = subj;
	}
	public String getTitle() {
		return title;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public String getDescription() {
		return description;
	}
	public String getSubject() {
		return subject;
	}
	public void setPhoneNotification(Date date) {
		// TODO: work on notification date
		notificationDate = date;
	}
	public Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dueDate);
		return cal;
	}
}
