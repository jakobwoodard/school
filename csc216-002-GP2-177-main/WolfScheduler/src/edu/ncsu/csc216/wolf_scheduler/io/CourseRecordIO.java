/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Class to read and write Course Records
 * @author Jakob Woodard
 *
 */
public class CourseRecordIO {
	/**
	 * Reads in a given course record and generates a valid list of Courses. Invalid Courses
	 * are ignored and a FNFE is thrown if permissions are incorrect or if a file cannot be
	 * read
	 * @param fileName name of file being read in
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if no file could be found
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
						break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	        	fileReader.nextLine();
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}
	/**
	 * Helper method for the readCourseRecords method
	 * @param nextLine the next line of code being read
	 * @return null no info is needed for return
	 */
	private static Course readCourse(String nextLine) {
		Scanner in = new Scanner(nextLine);
		in.useDelimiter(",");
		String name = "";
		String title = "";
		String section = "";
		int credits = 0;
		String instructorId = "";
		String meetingDays = "";
		int startTime = 0;
		int endTime = 0;
		while (in.hasNext()) {
			try {
				name = in.next();
				title = in.next();
				section = in.next();
				credits = in.nextInt();
				instructorId = in.next();
				meetingDays = in.next();
				if ("A".equals(meetingDays)) {
					break;
				}
				startTime = in.nextInt();
				endTime = in.nextInt();
			}
			catch (NoSuchElementException e) {
				throw new IllegalArgumentException();
			}
		}
		in.close();
		if ("A".equals(meetingDays)) {
			Course course = new Course(name, title, section, credits, instructorId, meetingDays);
			startTime = 0;
			endTime = 0;
			return course;
		}
		else {
			Course course = new Course(name, title, section, credits, instructorId, meetingDays,
			startTime, endTime);
			return course;
		}
	}

}
