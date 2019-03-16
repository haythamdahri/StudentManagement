package com.student.manager.service;

import com.student.manager.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StudentService {

    public void saveStudent(Student student);
    public boolean deleteStudent(int id);
    public Student getStudent(int id);
    public List<Student> getStudents();
    public Page<Student> getPagedStudents(int page, int size);
    public Page<Student> getPagedSearchStudents(String search, int page, int size);

}
