package cf.jtarget.seminars.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cf.jtarget.seminars.serializer.SeminarIdOnly;
import cf.jtarget.seminars.serializer.StudentIdOnly;

@Entity
@Table(name = "APP_PROGRESS")
public class Progress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonSerialize(using = StudentIdOnly.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private Student student;
	@JsonSerialize(using = SeminarIdOnly.class) 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEMINAR_ID", nullable = false)
	private Seminar seminar;
	@ElementCollection
	@CollectionTable(name = "PROGRESS_MARKS", joinColumns = @JoinColumn(name = "PROGRESS_ID"))
	@Column(name = "MARKS")
	private List<Integer> marks;

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
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student instance) {
		this.student = instance;
	}

	/**
	 * @return the seminar
	 */
	public Seminar getSeminar() {
		return seminar;
	}

	/**
	 * @param seminar
	 *            the seminar to set
	 */
	public void setSeminar(Seminar instance) {
		this.seminar = instance;
	}

	/**
	 * @return the marks
	 */
	public List<Integer> getMarks() {
		return marks;
	}

	/**
	 * @param marks
	 *            the marks to set
	 */
	public void setMarks(List<Integer> marks) {
		this.marks = marks;
	}

}
