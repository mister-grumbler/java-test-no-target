/**
 * 
 */
package cf.jtarget.seminars.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;

/**
 * @author dron
 *
 */
@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
	List<Progress> findByStudent(Student student);
	List<Progress> findBySeminar(Seminar seminar);
}
