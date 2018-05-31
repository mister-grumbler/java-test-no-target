package cf.jtarget.seminars.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "APP_PROGRESS")
public class SeminarProgress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "STUDENT_ID", nullable = false)
	private Student student;
	@Column(name = "SEMINAR_ID", nullable = false)
	private Seminar seminar;
	@ElementCollection
	@CollectionTable(name = "PROGRESS_MARKS", joinColumns = @JoinColumn(name = "PROGRESS_ID"))
	@Column(name = "MARKS")
	private List<Integer> marks;

}
