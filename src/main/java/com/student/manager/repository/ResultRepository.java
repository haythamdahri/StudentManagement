package com.student.manager.repository;

import com.student.manager.entity.Result;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "/rest-students")
public interface ResultRepository extends PagingAndSortingRepository<Result, Integer> {

}
