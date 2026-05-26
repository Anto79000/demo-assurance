package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Component
public class AnswerRepository {
    public List<Answer> findByQuestionId(Long questionId) {
        return null;
    };
}
