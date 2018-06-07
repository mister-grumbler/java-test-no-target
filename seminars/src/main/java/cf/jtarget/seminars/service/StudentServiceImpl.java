/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Student;

/**
 * @author dron
 *
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#isExist(cf.jtarget.seminars.model.Student)
	 */
	@Override
	public boolean isExist(Student instance) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#findById(java.lang.Long)
	 */
	@Override
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#findByName(java.lang.String)
	 */
	@Override
	public Student findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#save(cf.jtarget.seminars.model.Student)
	 */
	@Override
	public void save(Student instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#update(cf.jtarget.seminars.model.Student)
	 */
	@Override
	public void update(Student instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.StudentService#getAll()
	 */
	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
