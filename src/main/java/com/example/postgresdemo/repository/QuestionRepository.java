package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public class QuestionRepository {
    public Optional<Question> findById(Long questionId) {
        return null;
    }
}
