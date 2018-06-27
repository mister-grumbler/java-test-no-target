/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.repository.ProgressRepository;

/**
 * @author dron
 *
 */
@Service("ProgressService")
public class ProgressServiceImpl implements ProgressService {
	@Autowired
	private ProgressRepository repo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.ProgressService#findById(java.lang.Long)
	 */
	@Override
	public Progress findById(Long id) {
		return repo.getOne(id);
	}

	@Override
	public Progress findByIds(Long studentId, Long seminarId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProgressService#listByStudent(cf.jtarget.seminars.model.Student)
	 */
	@Override
	public List<Progress> findByStudent(Student student) {
		return repo.findByStudent(student);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProgressService#listBySeminar(cf.jtarget.seminars
	 * .model.Seminar)
	 */
	@Override
	public List<Progress> findBySeminar(Seminar seminar) {
		return repo.findBySeminar(seminar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProgressService#save(cf.jtarget.seminars.model.
	 * Progress)
	 */
	@Override
	public void save(Progress instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProgressService#update(cf.jtarget.seminars.model.
	 * Progress)
	 */
	@Override
	public void update(Progress instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.ProgressService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		repo.delete(id);
	}

}
