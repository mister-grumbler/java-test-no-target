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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "APP_PROGRESS")
public class Progress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Student student;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEMINAR_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Seminar seminar;
	@ElementCollection
	@CollectionTable(name = "PROGRESS_MARKS", joinColumns = @JoinColumn(name = "PROGRESS_ID"))
	@Column(name = "MARKS")
	private List<Integer> marks;

}
