package edu.mum.cs.cs425.eRegistrar.service.impl;

import edu.mum.cs.cs425.eRegistrar.model.Student;
import edu.mum.cs.cs425.eRegistrar.repository.StudentRepository;
import edu.mum.cs.cs425.eRegistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

//@Service(value = "MainStudentService")
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Iterable<Student> getAllStudents() {
//        return ((List<Student>)repository.findAll())
//                .stream()
//                .sorted(Comparator.comparing(Student::getTitle))
//                .collect(Collectors.toList());
        return repository.findAll(Sort.by("studentNumber"));
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 3, Sort.by("studentNumber")));
    }

    @Override
    public Student saveStudent(Student Student) {
        return repository.save(Student);
    }

    @Override
    public Student getStudentById(Integer StudentId) {
        return repository.findById(StudentId).orElse(null);
    }

    @Override
    public void deleteStudentById(Integer StudentId) {
        repository.deleteById(StudentId);
    }

    @Override
    public Optional<Student> findByNumber(String number) {
        return repository.findStudentByStudentNumber(number);
    }

}
