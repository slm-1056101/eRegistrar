package edu.mum.cs.cs425.eRegistrar.service;
import edu.mum.cs.cs425.eRegistrar.model.Student;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StudentService {

    public abstract Iterable<Student> getAllStudents();
    public abstract Page<Student> getAllStudentsPaged(int pageNo);
    public abstract Student saveStudent(Student Student);
    public abstract Student getStudentById(Integer StudentId);
    public abstract void deleteStudentById(Integer StudentId);
    public abstract Optional<Student> findByNumber(String isbn);

}
