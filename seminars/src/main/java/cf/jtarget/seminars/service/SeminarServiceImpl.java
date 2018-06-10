/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.repositories.SeminarRepository;

/**
 * @author dron
 *
 */
@Service("SeminarService")
public class SeminarServiceImpl implements SeminarService {
	@Autowired
	private SeminarRepository repo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.SeminarService#isExist(cf.jtarget.seminars.model.
	 * Seminar)
	 */
	@Override
	public boolean isExist(Seminar instance) {
		return repo.findByName(instance.getName()) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.SeminarService#findById(java.lang.Long)
	 */
	@Override
	public Seminar findById(Long id) {
		return repo.getOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.SeminarService#findByName(java.lang.String)
	 */
	@Override
	public Seminar findByName(String name) {
		return repo.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.SeminarService#save(cf.jtarget.seminars.model.
	 * Seminar)
	 */
	@Override
	public void save(Seminar instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.SeminarService#update(cf.jtarget.seminars.model.
	 * Seminar)
	 */
	@Override
	public void update(Seminar instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.SeminarService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		repo.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.SeminarService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		repo.delete(repo.findByName(name));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.SeminarService#getAll()
	 */
	@Override
	public List<Seminar> getAll() {
		return repo.findAll();
	}

}
