/**
 * 
 */
package cf.jtarget.seminars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;

/**
 * @author dron
 *
 */
public interface ProgressRepository extends JpaRepository<Progress, Long> {
	List<Progress> listByStudent(Student student);
	List<Progress> listBySeminar(Seminar seminar);
}
