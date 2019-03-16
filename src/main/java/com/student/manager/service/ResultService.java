package com.student.manager.service;

import com.student.manager.entity.Result;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ResultService {

    public void saveResult(Result result);
    public boolean deleteResult(int id);
    public Result getResult(int id);
    public List<Result> getResults();
    public Page<Result> getPagedResults(int page, int size);

}
