package data;

import java.util.*;

public class Assignment {
	private String title;
	private Date dueDate;
	private String description;
	private Date notificationDate; // TODO: use Twilio API to send to users
	
	private Long psAssID; // assignment ID from powerschool
	private Long psCourseID; // class ID from powerschool
	
	/**
	 * Creates a new <code>CalendarEvent</code>, which holds information for
	 * a pending date of an assignment item.
	 * @param title A short, descriptive title of the event.
	 * @param due The due date of the assignment.
	 * @param descr A more verbose description of the event.
	 * @param subj A predetermined scholarly subject of the event.
	 */
	public Assignment(String title, Date due, String descr, Long psId, Long psSecID) {
		this.title = title;
		dueDate = due;
		description = descr;
		
		psAssID = psId; // assignment ID
		psCourseID = psSecID; // course ID
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
	public void setPhoneNotification(Date date) {
		// TODO: work on notification date
		notificationDate = date;
	}
	@Override
	public String toString() {
		return title;
	}
}
