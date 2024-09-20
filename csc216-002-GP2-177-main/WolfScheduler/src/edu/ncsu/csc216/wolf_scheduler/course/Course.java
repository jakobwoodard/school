/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;
/**
 * Class for adding/creating Courses
 * @author Jakob Woodard
 *
 */
public class Course extends Activity {
	/** Minimum length of a name */
	private static final int MIN_NAME_LENGTH = 5;
	/** Maximum length of a name */
	private static final int MAX_NAME_LENGTH = 8;
	/** Minimum amount of letters */
	private static final int MIN_LETTER_COUNT = 1;
	/** Maximum amount of letters */
	private static final int MAX_LETTER_COUNT = 4;
	/** Amount of digits */
	private static final int DIGIT_COUNT = 3;
	/** Length of the section */
	private static final int SECTION_LENGTH = 3;
	/** Maximum amount of credits */
	private static final int MAX_CREDITS = 5;
	/** Minimum amount of credits */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Constructs a Course object with values for all fields
	 * @param name name of the Course
	 * @param title title of the Course
	 * @param section section of the Course
	 * @param credits credit hours for the Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for the Course as a series of chars
	 * @param startTime starting time of the Course
	 * @param endTime ending time of the Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}
	

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for
	 * courses that are arranged
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for the Course as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name.
	 * @param name the name to set
	 * @throws IllegalArguemntExpection if null
	 * @throws IllegalArgumentExecption if less than 5 characters or more than 8
	 * @throws IllegalArgumentException if name doesn't contain a space between letter and number characters
	 * @throws IllegalArgumentExecption if name doesn't start with 1-4 letter characters
	 * @throws IllegalArgumentException if name doesn't end with 3 digit characters
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Name's length should be between 5 and 8, inclusive.");
		}
		
		int letterCount = 0;
		int digitCount = 0;
		boolean space = false;
		//Scanner in = new Scanner(name);
		for (int i = 0; i <= name.length() - 1; i++) {
			char c = name.charAt(i);
			String character = Character.toString(c);
			if (!space) {
				if (Character.isLetter(c)) {
					letterCount++;
				}
				else if (" ".equals(character)) {
					space = true;
				}
				else {
					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");	
					}
			}
			
			else if (space) {
				if (Character.isDigit(c)) {
					digitCount++;
				}
				else {
					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
				}	
			}
		}
		//Check that the number of letters is correct
		if (letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}
			
		if (digitCount != DIGIT_COUNT) {
			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}
	this.name = name;
	}
	

	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * @param section the section to set
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i <= section.length() - 1; i++) {
			char c = section.charAt(i);
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("Section should be three digits.");			
			}
		}
		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		try {
			String s = String.valueOf(credits);
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			throw new NumberFormatException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's Instructor
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's Instructor
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	/**
	 * Method to create a string of a Course
	 * @return the string of the course.
	 */
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}
	
	/**
	 * Checks to see if an activity is a duplicate or not
	 * @param activity the activity checking for a repeat
	 * @return true if it is a duplicate
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			if (((Course) activity).getName().equals(getName())) {
				return true;
			}
			return false;
		}
		return false;
	}


	/**
	 * Overrides setMeetingDaysAndTime to be usable for just Course implementation.
	 * @param meetingDays a string of characters representing meeting days
	 * @param startTime the start time of the Course
	 * @param endTime the end time of the Course
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
		else {
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
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
				else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
				
			}
			
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException ("Invalid meeting days.");
			}
			
			
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}


	/**
	 * Creates the hashCode for a Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Checks to see that two Courses are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Creates an array for a display with name, section, title, and meeting string.
	 * @return the shortened array.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay =  new String[4];
		shortDisplay[0] = getName();
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		return shortDisplay;
	}

	/**
	 * Creates an array for a display with name, section, title, credits, instructorId, and meeting string
	 * as well as an empty string for an event
	 * @return the longer array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay =  new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = Integer.toString(getCredits());
		longDisplay[4] = getInstructorId();
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}
	
	

}
