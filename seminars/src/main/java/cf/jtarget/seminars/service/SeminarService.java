/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import cf.jtarget.seminars.model.Seminar;

/**
 * @author dron
 *
 */
public interface SeminarService {
    boolean isExist(Seminar instance);

    Seminar findById(Long id);

    Seminar findByName(String name);

    void save(Seminar instance);

    void update(Seminar instance);

    void deleteById(Long id);

    void deleteByName(String name);

    List<Seminar> getAll();

}
