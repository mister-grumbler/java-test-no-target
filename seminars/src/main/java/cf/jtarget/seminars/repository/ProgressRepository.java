/**
 * 
 */
package cf.jtarget.seminars.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.jtarget.seminars.model.Progress;

/**
 * @author dron
 *
 */
@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
	List<Progress> findByStudentId(Long id);
	List<Progress> findBySeminarId(Long id);
}
