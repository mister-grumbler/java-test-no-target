/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import cf.jtarget.seminars.model.Progress;

/**
 * @author dron
 *
 */
public interface ProgressService {
    boolean isExist(Progress instance);

    Progress findById(Long id);

    Progress findByName(String name);

    void save(Progress instance);

    void update(Progress instance);

    void deleteById(Long id);

    void deleteByName(String name);

    List<Progress> getAll();


}
