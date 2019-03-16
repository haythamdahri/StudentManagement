package com.student.manager.service;

import com.student.manager.entity.Student;
import com.student.manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(int id) {
       /* Query query = this.entitytManager.createQuery("from Student s where s.id=:studentId");
        query.setParameter("studentId", id);
        int deletedRows = query.executeUpdate();
        return deletedRows>0 ? true:  false;*/
       this.studentRepository.deleteById(id);
       return true;
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> optional = this.studentRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        Iterable<Student> studentsIterator = this.studentRepository.findAll();
        studentsIterator.forEach(students::add);
        return students;
    }
    @Override
    public Page<Student> getPagedStudents(int page, int size) {
        Page<Student> studentPage = this.studentRepository.findAll(new PageRequest(page, size));
        return studentPage;
    }

    @Override
    public Page<Student> getPagedSearchStudents(String search, int page, int size) {
        Page<Student> studentPage = this.studentRepository.findBySearch(search, new PageRequest(page, size));

        return studentPage;
    }


}
