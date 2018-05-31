package cf.jtarget.seminars.model;

import java.io.Serializable;

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

@Entity
@Table(name = "APP_SEMINAR")
public class Seminar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
/*	@OneToMany(mappedBy = "seminar", cascade = CascadeType.ALL)
	private Set<SeminarProgress> progress; */
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
}
