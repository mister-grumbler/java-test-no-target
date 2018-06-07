/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import cf.jtarget.seminars.model.Professor;

/**
 * @author dron
 *
 */
public interface ProfessorService {
    boolean isExist(Professor instance);

    Professor findById(Long id);

    Professor findByName(String name);

    void save(Professor instance);

    void update(Professor instance);

    void deleteById(Long id);

    void deleteByName(String name);

    List<Professor> getAll();

}
