package cf.jtarget.seminars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cf.jtarget.seminars.serializer.ProfessorAttachById;
import cf.jtarget.seminars.serializer.ProfessorIdOnly;

@Entity
@Table(name = "APP_SEMINAR")
public class Seminar {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonSerialize(using = ProfessorIdOnly.class)
	@JsonDeserialize(using = ProfessorAttachById.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFESSOR_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Professor lecturer;
	@Column(name = "NUMBER")
	private Integer number;
	@Column(name = "NAME")
	private String name;
	@Column(name = "FEE")
	private Float fee;

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
	 * @return the lecturer
	 */
	public Professor getLecturer() {
		return lecturer;
	}

	/**
	 * @param lecturer
	 *            the lecturer to set
	 */
	public void setLecturer(Professor lecturer) {
		this.lecturer = lecturer;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
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
	 * @return the fee
	 */
	public Float getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
	 */
	public void setFee(Float fee) {
		this.fee = fee;
	}
}
