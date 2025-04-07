package org.example.quest.service;

import org.example.quest.entity.Answer;
import org.example.quest.entity.Question;
import org.example.quest.repository.QuestionAndAnswerRepository;


public class QuestionAndAnswerService {

    private final QuestionAndAnswerRepository repository = new QuestionAndAnswerRepository();

    public Question getQuestionById(int id) {
        return repository.getQuestionById(id);
    }
    public Answer getAnswerById(int id) {
        return repository.getAnswerById(id);
    }
}
