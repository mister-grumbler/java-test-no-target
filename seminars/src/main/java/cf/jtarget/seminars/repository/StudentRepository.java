package cf.jtarget.seminars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.jtarget.seminars.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByName(String name);
}
