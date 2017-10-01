package com.mjkim.springmvc.step2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Student implements Serializable {
	@Size(min = 3, max = 30)
	private String firstName;
	@Size(min = 3, max = 30)
	private String lastName;
	@NotEmpty
	private String gender;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	@NotNull
	private Date dob;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	private String section;
	@NotEmpty
	private String country;
	private boolean firstAttempt;
	@NotEmpty
	private List<String> subjects = new ArrayList<String>();

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section
	 *            the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the firstAttempt
	 */
	public boolean isFirstAttempt() {
		return firstAttempt;
	}

	/**
	 * @param firstAttempt
	 *            the firstAttempt to set
	 */
	public void setFirstAttempt(boolean firstAttempt) {
		this.firstAttempt = firstAttempt;
	}

	/**
	 * @return the subjects
	 */
	public List<String> getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects
	 *            the subjects to set
	 */
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

}
