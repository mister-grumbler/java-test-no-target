/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Professor;

/**
 * @author dron
 *
 */
@Service("ProfessorService")
public class ProfessorServiceImpl implements ProfessorService {

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#isExist(cf.jtarget.seminars.model.Professor)
	 */
	@Override
	public boolean isExist(Professor instance) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#findById(java.lang.Long)
	 */
	@Override
	public Professor findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#findByName(java.lang.String)
	 */
	@Override
	public Professor findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#save(cf.jtarget.seminars.model.Professor)
	 */
	@Override
	public void save(Professor instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#update(cf.jtarget.seminars.model.Professor)
	 */
	@Override
	public void update(Professor instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProfessorService#getAll()
	 */
	@Override
	public List<Professor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
