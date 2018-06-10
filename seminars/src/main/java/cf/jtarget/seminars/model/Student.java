package cf.jtarget.seminars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_STUDENT")
public class Student {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "MARKSBOOK")
	private Integer marksBook;
	@Column(name = "MARKSAVERAGE")
	private Float marksAverage;

	// private Set<Progress> progress;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the marksBook
	 */
	public Integer getMarksBook() {
		return marksBook;
	}

	/**
	 * @param marksBook
	 *            the marksBook to set
	 */
	public void setMarksBook(Integer marksBook) {
		this.marksBook = marksBook;
	}

	/**
	 * @return the marksAverage
	 */
	public Float getMarksAverage() {
		return marksAverage;
	}

	/**
	 * @param marksAverage
	 *            the marksAverage to set
	 */
	public void setMarksAverage(Float marksAverage) {
		this.marksAverage = marksAverage;
	}
}
