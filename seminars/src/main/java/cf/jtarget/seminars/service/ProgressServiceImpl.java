/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Progress;

/**
 * @author dron
 *
 */
@Service("ProgressService")
public class ProgressServiceImpl implements ProgressService {

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#isExist(cf.jtarget.seminars.model.Progress)
	 */
	@Override
	public boolean isExist(Progress instance) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#findById(java.lang.Long)
	 */
	@Override
	public Progress findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#findByName(java.lang.String)
	 */
	@Override
	public Progress findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#save(cf.jtarget.seminars.model.Progress)
	 */
	@Override
	public void save(Progress instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#update(cf.jtarget.seminars.model.Progress)
	 */
	@Override
	public void update(Progress instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.ProgressService#getAll()
	 */
	@Override
	public List<Progress> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
