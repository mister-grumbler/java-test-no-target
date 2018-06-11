/**
 * 
 */
package cf.jtarget.seminars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.jtarget.seminars.model.Seminar;

/**
 * @author dron
 *
 */
public interface SeminarRepository extends JpaRepository<Seminar, Long> {
	Seminar findByName(String name);
}
