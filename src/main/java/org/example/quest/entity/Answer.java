package org.example.quest.entity;

public class Answer {
    private int id;
    private String buttonText;
    private String actionText;
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