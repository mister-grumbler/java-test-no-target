/**
 * 
 */
package cf.jtarget.seminars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.jtarget.seminars.model.Professor;

/**
 * @author dron
 *
 */
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
