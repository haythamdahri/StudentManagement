package com.student.manager.service;

import com.student.manager.entity.Result;
import com.student.manager.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public void saveResult(Result result) {
        this.resultRepository.save(result);
    }

    @Override
    public boolean deleteResult(int id) {
       /* Query query = this.entitytManager.createQuery("from Student s where s.id=:studentId");
        query.setParameter("studentId", id);
        int deletedRows = query.executeUpdate();
        return deletedRows>0 ? true:  false;*/
       this.resultRepository.deleteById(id);
       return true;
    }

    @Override
    public Result getResult(int id) {
        Optional<Result> optional = this.resultRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public List<Result> getResults() {
        List<Result> results = new ArrayList<>();
        Iterable<Result> resultIterable = this.resultRepository.findAll();
        resultIterable.forEach(results::add);
        return results;
    }
    @Override
    public Page<Result> getPagedResults(int page, int size) {
        Page<Result> resultPage = this.resultRepository.findAll(new PageRequest(page, size));
        return resultPage;
    }


}
