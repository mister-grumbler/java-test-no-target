/**
 * 
 */
package cf.jtarget.seminars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.jtarget.seminars.model.Professor;

/**
 * @author dron
 *
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	Professor findByName(String name);
}
