/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.repository.StudentRepository;

/**
 * @author dron
 *
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.StudentService#isExist(java.lang.Long)
	 */
	@Override
	public boolean isExist(Long id) {
		return repo.exists(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.StudentService#findById(java.lang.Long)
	 */
	@Override
	public Student findById(Long id) {
		return repo.getOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.StudentService#findByName(java.lang.String)
	 */
	@Override
	public Student findByName(String name) {
		return repo.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.StudentService#save(cf.jtarget.seminars.model.
	 * Student)
	 */
	@Override
	public void save(Student instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.StudentService#update(cf.jtarget.seminars.model.
	 * Student)
	 */
	@Override
	public void update(Student instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.StudentService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		repo.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.StudentService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		repo.delete(repo.findByName(name));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.StudentService#getAll()
	 */
	@Override
	public List<Student> getAll() {
		return repo.findAll();
	}

}
