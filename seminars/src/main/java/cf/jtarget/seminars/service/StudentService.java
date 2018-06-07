/**
 * 
 */
package cf.jtarget.seminars.service;

import java.util.List;

import cf.jtarget.seminars.model.Student;

/**
 * @author dron
 *
 */
public interface StudentService {
    boolean isExist(Student instance);

    Student findById(Long id);

    Student findByName(String name);

    void save(Student instance);

    void update(Student instance);

    void deleteById(Long id);

    void deleteByName(String name);

    List<Student> getAll();

}
