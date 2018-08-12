/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import cf.jtarget.seminars.model.Progress;

/**
 * @author dron
 *
 */
public interface ProgressService {
	boolean isExist(Long id);

	Progress findById(Long id);

	List<Progress> findByStudentId(Long id);

	List<Progress> findBySeminarId(Long id);

	Progress findByIds(Long studentId, Long seminarId);

	void save(Progress instance);

	void update(Progress instance);

	void deleteById(Long id);

	List<Progress> getAll();

}
