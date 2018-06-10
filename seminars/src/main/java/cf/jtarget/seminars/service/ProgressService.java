/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;

/**
 * @author dron
 *
 */
public interface ProgressService {
    Progress findById(Long id);

    List<Progress> listByStudent(Student instance);
    
    List<Progress> listBySeminar(Seminar instance);

    void save(Progress instance);

    void update(Progress instance);

    void deleteById(Long id);

}
