package edu.ncsu.csc216.wolf_scheduler.course;
/**
 * Superclass that includes name, meeting days, and meeting times.
 * @author Jakob Woodard
 *
 */
public abstract class Activity {

	/** Highest hour number */
	private static final int UPPER_HOUR = 24;
	/** Highest minute number */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Getter for a shorter display
	 * @return 1D short array of Activity info
	 */
	public abstract String[] getShortDisplayArray();
	/** Getter for a longer display
	 * @return 1D long array of Activity info
	 */
	public abstract String[] getLongDisplayArray();
	/** Abstract test to see if an activity is a duplicate
	 * @param activity the activity being tested
	 * @return true if the activity is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);
	/**
	 * Constructor method for an Activity
	 * @param title title of the activity
	 * @param meetingDays meeting days of the activity as a string of characters
	 * @param startTime start time of the activity
	 * @param endTime end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Course's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days as well as start and end times for Course.
	 * @param meetingDays meeting days expressed as a series of chars
	 * @param startTime start time of Course
	 * @param endTime end time of Course
	 * @throws IllegalArgumentException if meetingDays is null or empty
	 * @throws IllegalArgumentException if there are duplicate weekdays
	 * @throws IllegalArgumentException if starting hour is invalid
	 * @throws IllegalArgumentException if starting minute is invalid
	 * @throws IllegalArgumentException if ending hour is invalid
	 * @throws IllegalArgumentException if ending minute is invalid
	 * @throws IllegalArgumentException if start time is not a valid military time
	 * @throws IllegalArgumentException if end time is not a valid military time
	 * @throws IllegalArgumentException if end time is less than start time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		if ("A".equals(meetingDays)) {
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
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
			
			
			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;
			
			if (startTime < 0 || startTime >= 2400) {
				throw new IllegalArgumentException("Invalid start time.");
			}
			if (endTime < 0 || endTime >= 2400) {
				throw new IllegalArgumentException("Invalid end time.");
			}
			if (startHour < 0 || startHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid start time.");
			}
			if (startMin < 0 || startMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid start time.");
			}
			if (endHour < 0 || endHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid end time.");
			}
			if (endMin < 0 || endMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid end time.");
			}
			if (startTime > endTime) {
				throw new IllegalArgumentException("End time cannot be before start time.");
			}
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
			
		}
	}

	/**
	 * Returns the Course's start time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Helper method to convert military time into normal time
	 * @param time the military time you want to convert
	 * @return a string of the normal time followed by either "AM" or "PM" depending
	 */
	public String getTimeString(int time) {
		String classTime = "";
		String extraZero = "00";
		int milHour = time / 100;
		int min = time % 100;
		int normTime = 0;
		
	
		if (milHour > 12) {
			normTime = milHour - 12;
			if (min == 0) {
				classTime = classTime + normTime + ":" + extraZero + "PM";
			}
			else {
				classTime = classTime + normTime + ":" + min + "PM";
			}
			
		}
		else if (milHour == 12) {
			if (min == 0) {
				classTime = classTime + milHour + ":" + extraZero + "PM";
			}
			else {
				classTime = classTime + milHour + ":" + min + "PM";
			}
		}
		else {
			normTime = milHour;
			if (min == 0) {
				classTime = classTime + normTime + ":" + extraZero + "AM";
			}
			else {
				classTime = classTime + normTime + ":" + min + "AM";
			}
		}
		return classTime;
	}

	/**
	 * Creates a string for the meeting days and times of a Course
	 * @return the string of days and times for the Course
	 */
	public String getMeetingString() {
		String meeting = "";
		String days = getMeetingDays();
		if (!"A".equals(days)) {
			int startingTime = getStartTime();
			int endingTime = getEndTime();
			String start = getTimeString(startingTime);
			String end = getTimeString(endingTime);
			meeting = meeting + days + " " + start + "-" + end;
		}
		else {
			meeting += "Arranged";
		}
		
		return meeting;
	}
	
	
	
	/**
	 * Creates the hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	/**
	 * Compares two Activities to make sure they are equal to each other
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}