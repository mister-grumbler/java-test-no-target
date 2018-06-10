/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.jtarget.seminars.model.Professor;
import cf.jtarget.seminars.repositories.ProfessorRepository;

/**
 * @author dron
 *
 */
@Service("ProfessorService")
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private ProfessorRepository repo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProfessorService#isExist(cf.jtarget.seminars.
	 * model.Professor)
	 */
	@Override
	public boolean isExist(Professor instance) {
		return repo.findByName(instance.getName()) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.ProfessorService#findById(java.lang.Long)
	 */
	@Override
	public Professor findById(Long id) {
		return repo.getOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProfessorService#findByName(java.lang.String)
	 */
	@Override
	public Professor findByName(String name) {
		return repo.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProfessorService#save(cf.jtarget.seminars.model.
	 * Professor)
	 */
	@Override
	public void save(Professor instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProfessorService#update(cf.jtarget.seminars.model
	 * .Professor)
	 */
	@Override
	public void update(Professor instance) {
		repo.save(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.ProfessorService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		repo.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cf.jtarget.seminars.service.ProfessorService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		repo.delete(repo.findByName(name));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cf.jtarget.seminars.service.ProfessorService#getAll()
	 */
	@Override
	public List<Professor> getAll() {
		return repo.findAll();
	}

}
