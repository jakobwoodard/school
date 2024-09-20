package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
/**
 * Compiling class for Course and CourseRecordIO. Creates an ArrayList of courses in a catalog
 * and allows for those courses to be added to a specified schedule in another ArrayList. The schedule can
 * also be modified in this class.
 * @author Jakob Woodard
 *
 */
public class WolfScheduler {
	
	/** title for the schedule */
	private String title;
	/** ArrayList of courses for schedule */
	private ArrayList<Activity> schedule;
	/** ArrayList of courses in the catalog */
	private ArrayList<Course> catalog;

	/**
	 * Constructor for the WolfScheduler
	 * @param fileName name of file with courses
	 * @throws IllegalArgumentException if a file cannot be found
	 */
	public WolfScheduler(String fileName) {
		this.schedule = new ArrayList<Activity>();
		this.title = "My Schedule";
		try {
			this.catalog = CourseRecordIO.readCourseRecords(fileName);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file");
		}
		
	}
	/**
	 * Getter method for the course catalog returns empty if the catalog is empty
	 * @return courses a 2D array of courses with name, title, and section fields.
	 */
	public String[][] getCourseCatalog() {
		if (catalog.size() == 0) {
			String courses[][] = {};
			return courses;
		}
		String[][] courses = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			String name = catalog.get(i).getName();
			String courseTitle = catalog.get(i).getTitle();
			String section = catalog.get(i).getSection();
			String info = catalog.get(i).getMeetingString();
			courses[i][0] = name;
			courses[i][1] = section;
			courses[i][2] = courseTitle;
			courses[i][3] = info;
		}
		
		return courses;
	}
	/**
	 * Getter method for scheduled courses
	 * @return scheduledCourses array of scheduled courses
	 */
	public String[][] getScheduledActivities() {
		
		if (schedule.size() == 0) {
			String[][] scheduledCourses = {};
			return scheduledCourses;
		}
		String[][] scheduledCourses = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			scheduledCourses[i][0] = schedule.get(i).getShortDisplayArray()[0];
			scheduledCourses[i][1] = schedule.get(i).getShortDisplayArray()[1];
			scheduledCourses[i][2] = schedule.get(i).getShortDisplayArray()[2];
			scheduledCourses[i][3] = schedule.get(i).getShortDisplayArray()[3];
		}
		return scheduledCourses;
	}
	/**
	 * Getter method for full schedule
	 * @return fullScheduledCourses a 2D array of all of the scheduled courses and their details
	 */
	public String[][] getFullScheduledActivities() {
		if (schedule.size() == 0) {
			String[][] fullScheduledCourses = {};
			return fullScheduledCourses;
		}
		String[][] fullScheduledCourses = new String[schedule.size()][7];
		for (int i = 0; i < schedule.size(); i++) {
			fullScheduledCourses[i][0] = schedule.get(i).getLongDisplayArray()[0];
			fullScheduledCourses[i][1] = schedule.get(i).getLongDisplayArray()[1];
			fullScheduledCourses[i][2] = schedule.get(i).getLongDisplayArray()[2];
			fullScheduledCourses[i][3] = schedule.get(i).getLongDisplayArray()[3];
			fullScheduledCourses[i][4] = schedule.get(i).getLongDisplayArray()[4];
			fullScheduledCourses[i][5] = schedule.get(i).getLongDisplayArray()[5];
			fullScheduledCourses[i][6] = schedule.get(i).getLongDisplayArray()[6];
		}
		return fullScheduledCourses;
	}
	/**
	 * Getter for ScheduleTitle
	 * @return title of schedule
	 */
	public String getScheduleTitle() {
		return title;
	}
	/**
	 * Writes a schedule to a specified file name
	 * @param fileName name of the file to be written to
	 * @throws IllegalArgumentException if the file cannot be saved to.
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
	/**
	 * Getter for Course from catalog. Method searches the catalog for a course with the same
	 * section number and name, returning it when found. If no such course is found, method 
	 * returns null
	 * @param name name of course searching for
	 * @param section section of course searching for
	 * @return course that has said name/section or null
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				Course wantedCourse = catalog.get(i);
				return wantedCourse;
			}
		}
		return null;
	}
	/**
	 * Boolean that returns true if a course is successfully added and false if not
	 * Throws an IAE if the course is a duplicate
	 * @param name name of desired course
	 * @param section section of desired course
	 * @return false if course is not added, true if added
	 */
	public boolean addCourseToSchedule(String name, String section) {
		if (getCourseFromCatalog(name, section) == null) {
			return false;
		}
		Course newCourse = getCourseFromCatalog(name, section);
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(newCourse)){
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(newCourse);
		return true;
	}
	/**
	 * Boolean that returns true if a course is successfully added and false if not
	 * Throws an IAE if already enrolled in the event
	 * @param title title of the event
	 * @param meetingDays meeting days of the event
	 * @param startTime start time of the event
	 * @param endTime end time of the event
	 * @param weeklyRepeat how many times the event repeats weekly
	 * @param eventDetails details of the event
	 * @return true if successfully added
	 */
	public boolean addEventToSchedule(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		Event newEvent = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(newEvent)) {
				throw new IllegalArgumentException("You have already created an event called " + title);
			}
		}
		schedule.add(newEvent);
		return true;
	}
	/**
	 * Boolean to see if a course is in a schedule and has been removed
	 * @param idx TODO
	 * @return true if course is in schedule and was removed
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		}
		catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Resets the schedule to an empty ArrayList
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Activity>();
		
	}
	/**
	 * Setter method for schedule title
	 * @param title new title of the schedule
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
		
	}

}
