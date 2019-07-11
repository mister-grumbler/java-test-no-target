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
 * The POJO being used during serialization and deserialization as holder of
 * root structure of JSON file.
 * 
 * @author dron
 *
 */
public class RootHolder {

	private List<Professor> professors;
	private List<Progress> progresses;
	private List<Seminar> seminars;
	private List<Student> students;

	/**
	 * Constructor of new RootHolder object using exact data for fields
	 * 
	 * @param professors
	 * @param progresses
	 * @param seminars
	 * @param students
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
	 * Constructor of new RootHolder object with empty lists
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((professors == null) ? 0 : professors.hashCode());
		result = prime * result + ((progresses == null) ? 0 : progresses.hashCode());
		result = prime * result + ((seminars == null) ? 0 : seminars.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RootHolder))
			return false;
		RootHolder other = (RootHolder) obj;
		if (professors == null) {
			if (other.professors != null)
				return false;
		} else if (!professors.equals(other.professors))
			return false;
		if (progresses == null) {
			if (other.progresses != null)
				return false;
		} else if (!progresses.equals(other.progresses))
			return false;
		if (seminars == null) {
			if (other.seminars != null)
				return false;
		} else if (!seminars.equals(other.seminars))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RootHolder [professors=" + professors + ", progresses=" + progresses + ", seminars=" + seminars
				+ ", students=" + students + "]";
	}

}
