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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cf.jtarget.seminars.serializer.SeminarAttachById;
import cf.jtarget.seminars.serializer.SeminarIdOnly;
import cf.jtarget.seminars.serializer.StudentAttachById;
import cf.jtarget.seminars.serializer.StudentIdOnly;

@Entity
@Table(name = "APP_PROGRESS")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Progress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonSerialize(using = StudentIdOnly.class)
	@JsonDeserialize(using = StudentAttachById.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Student student;
	@JsonSerialize(using = SeminarIdOnly.class)
	@JsonDeserialize(using = SeminarAttachById.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEMINAR_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
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
