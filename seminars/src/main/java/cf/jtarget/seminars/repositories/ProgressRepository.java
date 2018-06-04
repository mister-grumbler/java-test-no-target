/**
 * 
 */
package cf.jtarget.seminars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.jtarget.seminars.model.Progress;

/**
 * @author dron
 *
 */
public interface ProgressRepository extends JpaRepository<Progress, Long> {

}
