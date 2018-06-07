/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Seminar;

/**
 * @author dron
 *
 */
@Service("SeminarService")
public class SeminarServiceImpl implements SeminarService {

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#isExist(cf.jtarget.seminars.model.Seminar)
	 */
	@Override
	public boolean isExist(Seminar instance) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#findById(java.lang.Long)
	 */
	@Override
	public Seminar findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#findByName(java.lang.String)
	 */
	@Override
	public Seminar findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#save(cf.jtarget.seminars.model.Seminar)
	 */
	@Override
	public void save(Seminar instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#update(cf.jtarget.seminars.model.Seminar)
	 */
	@Override
	public void update(Seminar instance) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cf.jtarget.seminars.service.SeminarService#getAll()
	 */
	@Override
	public List<Seminar> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
