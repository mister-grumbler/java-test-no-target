/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.util.ArrayList;
import java.util.List;

import cf.jtarget.seminars.model.Professor;
import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;

/**
 * @author dron
 *
 */
public class RootHolder {

	private List<Professor> professors;
	private List<Progress> progresses;
	private List<Seminar> seminars;
	private List<Student> students;

	/**
	 * 
	 */
	public RootHolder(List<Professor> professors, List<Progress> progresses, List<Seminar> seminars,
			List<Student> students) {
		super();
		this.professors = professors;
		this.progresses = progresses;
		this.seminars = seminars;
		this.students = students;
	}

	/**
	 * 
	 */
	public RootHolder() {
		super();
		this.professors = new ArrayList<Professor>();
		this.progresses = new ArrayList<Progress>();
		this.seminars = new ArrayList<Seminar>();
		this.students = new ArrayList<Student>();
	}

	/**
	 * @return the professors
	 */
	public List<Professor> getProfessors() {
		return professors;
	}

	/**
	 * @param professors
	 *            the professors to set
	 */
	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	/**
	 * @return the progresses
	 */
	public List<Progress> getProgresses() {
		return progresses;
	}

	/**
	 * @param progresses
	 *            the progresses to set
	 */
	public void setProgresses(List<Progress> progresses) {
		this.progresses = progresses;
	}

	/**
	 * @return the seminars
	 */
	public List<Seminar> getSeminars() {
		return seminars;
	}

	/**
	 * @param seminars
	 *            the seminars to set
	 */
	public void setSeminars(List<Seminar> seminars) {
		this.seminars = seminars;
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
