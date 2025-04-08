package org.example.quest.controller;

import org.example.quest.entity.Answer;
import org.example.quest.entity.Question;
import org.example.quest.service.QuestionAndAnswerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "questServlet", value = "/quest-servlet")
public class QuestServlet extends HttpServlet {
    QuestionAndAnswerService service;


    @Override
    public void init() {
        service = new QuestionAndAnswerService();
    }

    public QuestionAndAnswerService getService() {
        return service;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String playerName = (String) session.getAttribute("playerName");
        Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
        if (gamesPlayed == null) gamesPlayed = 0;


        if (playerName == null) {
            req.setAttribute("gamesPlayed", gamesPlayed);
            try {
                req.getRequestDispatcher("/welcome.jsp").forward(req, res);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        String answerIdStr = req.getParameter("answerId");
        String questionIdStr = req.getParameter("id");
        String stepStr = req.getParameter("step");
        int step = (stepStr != null) ? Integer.parseInt(stepStr) : 1;


        if (answerIdStr != null) {
            int answerId = Integer.parseInt(answerIdStr);
            Answer chosenAnswer = service.getAnswerById(answerId);

            if (chosenAnswer != null && step == 1) {
                req.setAttribute("actionText", chosenAnswer.getActionText());
                req.setAttribute("nextId", chosenAnswer.getNextQuestionId());
                req.setAttribute("playerName", playerName);
                req.setAttribute("gamesPlayed", gamesPlayed);
                try {
                    req.getRequestDispatcher("/action.jsp").forward(req, res);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }


        int questionId = (questionIdStr == null) ? 1 : Integer.parseInt(questionIdStr);
        Question question = service.getQuestionById(questionId);

        if (question == null) {
            res.getWriter().println("<h1>Вопрос не найден</h1>");
            return;
        }


        if (question.getAnswers().isEmpty()) {
            gamesPlayed++;
            session.setAttribute("gamesPlayed", gamesPlayed);
        }


        req.setAttribute("question", question);
        req.setAttribute("answers", question.getAnswers());
        req.setAttribute("playerName", playerName);
        req.setAttribute("gamesPlayed", gamesPlayed);
        try {
            req.getRequestDispatcher("/quest.jsp").forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String name = req.getParameter("playerName");
        if (name != null && !name.isBlank()) {
            session.setAttribute("playerName", name);
        }
        res.sendRedirect(req.getContextPath() + "/quest-servlet");
    }

}
