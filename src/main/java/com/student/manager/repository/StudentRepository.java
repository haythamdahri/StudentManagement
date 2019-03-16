package com.student.manager.repository;

import com.student.manager.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "/rest-results")
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    @Query("select s from Student s where s.firstName like %:search% or s.lastName like %:search% or s.email like %:search% or s.degree like %:search%")
    public Page<Student> findBySearch(@Param("search") String search, @PageableDefault Pageable pageable);
}
