package com.student.manager.entity;

import javax.persistence.*;

/**
 * @author HAYTHAM DAHRI
 * @apiNote STUDENT PERSISTENCE CLASS - student table in the database
 * */

@Table
@Entity(name="result")
public class Result {

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="")
    private int id;

    @Column(name="mark")
    private float mark;

    @Column(name="module")
    private String module;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="student_id")
    private Student student;

    // Default constructor
    public Result(){

    }

    // Define constructor with arguments
    public Result(float mark, String module, Student student){
        this.mark = mark;
        this.module = module;
        this.student = student;
    }

    // Define Setters And Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
