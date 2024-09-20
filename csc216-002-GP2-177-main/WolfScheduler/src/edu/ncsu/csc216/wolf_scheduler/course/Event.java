/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class to make an event to add to a schedule
 * @author Jakob Woodard
 *
 */
public class Event extends Activity {
	/** Amount of times event is repeated */
	private int weeklyRepeat;
	/** Details for the event */
	private String eventDetails;
	
	
	
	/**
	 * Constructor for an event
	 * @param title title of the event
	 * @param meetingDays meeting days of the event
	 * @param startTime start time of the event
	 * @param endTime end time of the event
	 * @param weeklyRepeat amount of times the event is repeated in a week
	 * @param eventDetails details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Getter method for weeklyRepeat
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Setter method for weeklyRepeat
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Getter method for eventDetails
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Setter method for eventDetails
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}
	
	
	/**
	 * Checks to see if an activity is a duplicate or not
	 * @param activity the activity being checked for a repeat
	 * @return true if the event is a duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			if (((Event) activity).getTitle().equals(getTitle())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates an overridden shortened display with empty fields to accommodate an event's
	 * information
	 * @return shortDisplay the shortened array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		return shortDisplay;
	}
	/**
	 * Creates an overridden longer display with empty fields to accommodate an event's
	 * information
	 * @return longDisplay the longer array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = getEventDetails();
		return longDisplay;
	}
	/**
	 * Creates an overridden string that includes the amount of times the event
	 * occurs each week
	 * @return the new meeting string
	 */
	@Override
	public String getMeetingString() {
		String s = super.getMeetingString() + " (every " + getWeeklyRepeat() + " weeks)";
		return s;
	}
	
	/**
	 * Creates an overridden string that includes weeklyRepeat and event details
	 * @return the new String.
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + weeklyRepeat + "," + eventDetails;
	}
	/**
	 * Overrides setMeetingDaysAndTime to ensure that "A" is not a valid day and
	 * "U" and "S" are valid days
	 * @param meetingDays a string of meeting day characters
	 * @param startTime the event start time
	 * @param endTime the event end time
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		else {
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			int uCount = 0;
			int sCount = 0;
			for (int i = 0; i <= meetingDays.length() - 1; i++) {
				if (meetingDays.charAt(i) == 'M') {
					mCount++;	
				}
				else if (meetingDays.charAt(i) == 'T') {
					tCount++;	
				}
				else if (meetingDays.charAt(i) == 'W') {
					wCount++;	
				}
				else if (meetingDays.charAt(i) == 'H') {
					hCount++;	
				}
				else if (meetingDays.charAt(i) == 'F') {
					fCount++;	
				}
				else if (meetingDays.charAt(i) == 'U') {
					uCount++;
				}
				else if (meetingDays.charAt(i) == 'S') {
					sCount++;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
				
			}
			
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1 
					|| uCount > 1 || sCount > 1) {
				throw new IllegalArgumentException ("Invalid meeting days.");
			}
			
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	

}
