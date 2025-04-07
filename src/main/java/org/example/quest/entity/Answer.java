package org.example.quest.entity;

public class Answer {
    private int id;
    private String buttonText;     // Текст кнопки
    private String actionText;     // Текст, который показывается после выбора
    private int nextQuestionId;

    public Answer(int id, String buttonText, String actionText, int nextQuestionId) {
        this.id = id;
        this.buttonText = buttonText;
        this.actionText = actionText;
        this.nextQuestionId = nextQuestionId;
    }

    public int getId() {
        return id;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getActionText() {
        return actionText;
    }

    public int getNextQuestionId() {
        return nextQuestionId;
    }
}