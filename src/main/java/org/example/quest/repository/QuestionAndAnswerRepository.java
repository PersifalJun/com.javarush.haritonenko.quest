package org.example.quest.repository;

import org.example.quest.entity.Answer;
import org.example.quest.entity.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAndAnswerRepository {
    private static final Map<Integer, Question> questions = new HashMap<>();

    static {
        questions.put(1, new Question(1, "Ты потерял память. Принять вызов НЛО?", List.of(
                new Answer(1, "Отклонить вызов", "Ты отклонил вызов.", 2),
                new Answer(2, "Принять вызов", "Ты принял вызов.", 3)
        )));

        questions.put(2, new Question(2, "Поражение!", List.of()));

        questions.put(3, new Question(3, "Поднимаешься на мостик к капитану?", List.of(
                new Answer(3, "Отказаться", "Ты не пошел на переговоры.", 4),
                new Answer(4, "Подняться", "Ты поднялся на мостик.", 5)
        )));

        questions.put(4, new Question(4, "Поражение!", List.of()));

        questions.put(5, new Question(5, "Ты кто?", List.of(
                new Answer(5, "Солгать о себе", "Твоя ложь разоблачена.", 6),
                new Answer(6, "Рассказать правду", "Тебя вернули домой.", 7)
        )));

        questions.put(6, new Question(6, "Поражение!", List.of()));
        questions.put(7, new Question(7, "Победа!", List.of()));
    }

    public Question getQuestionById(int id) {
        return questions.get(id);
    }

    public Answer getAnswerById(int id) {
        for (Question question : questions.values()) {
            for (Answer answer : question.getAnswers()) {
                if (answer.getId() == id) {
                    return answer;
                }
            }
        }
        return null;
    }

}