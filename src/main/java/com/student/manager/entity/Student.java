package com.student.manager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HAYTHAM DAHRI
 * @apiNote STUDENT PERSISTENCE CLASS - student table in the database
 * */

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="degree")
    private String degree;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "student")
    private List<Result> results;


    // Default constructor
    public Student(){}

    // Constructor with arguments
    public Student(String firstName, String lastName, String email ,String degree){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.degree = degree;
    }

    // Define Setters And Getters For All Attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", degree='" + degree + '\'' +
                ", results=" + results +
                '}';
    }

    // Define convenient method to add a result object to the student
    public void addResult(Result result){

        if( this.results == null ){
            this.results = new ArrayList<>();
        }

        this.results.add(result);

    }
}
