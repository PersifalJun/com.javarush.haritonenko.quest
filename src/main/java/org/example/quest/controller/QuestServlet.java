package org.example.quest.controller;

import org.example.quest.entity.Answer;
import org.example.quest.entity.Question;
import org.example.quest.service.QuestionAndAnswerService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "questServlet", value = "/quest-servlet")
public class QuestServlet extends HttpServlet {
    private QuestionAndAnswerService service;

    @Override
    public void init() {
        service = new QuestionAndAnswerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        // Сессия: имя и счётчик игр
        String playerName = (String) session.getAttribute("playerName");
        Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
        if (gamesPlayed == null) gamesPlayed = 0;


        if (playerName == null) {
            out.println("<html><body>");
            out.println("<h1>Добро пожаловать в квест!</h1>");
            out.println("<form method='post' action='" + contextPath + "/quest-servlet'>");
            out.println("<label>Введите ваше имя:</label><br>");
            out.println("<input type='text' name='playerName' required/><br><br>");
            out.println("<button type='submit'>Начать квест</button>");
            out.println("</form>");
            out.println("<p>Сыграно игр: " + gamesPlayed + "</p>");
            out.println("</body></html>");
            return;
        }


        String answerIdStr = req.getParameter("answerId");
        String questionIdStr = req.getParameter("id");
        String stepStr = req.getParameter("step");
        int step = (stepStr != null) ? Integer.parseInt(stepStr) : 1;

        out.println("<html><body>");


        if (answerIdStr != null) {
            int answerId = Integer.parseInt(answerIdStr);
            Answer chosenAnswer = service.getAnswerById(answerId);

            if (chosenAnswer != null && step == 1) {
                if (chosenAnswer.getActionText() != null) {
                    out.println("<h2>" + chosenAnswer.getActionText() + "</h2>");
                }
                out.println("<form method='get' action='" + contextPath + "/quest-servlet'>");
                out.println("<input type='hidden' name='id' value='" + chosenAnswer.getNextQuestionId() + "'/>");
                out.println("<input type='hidden' name='step' value='2'/>");
                out.println("<button type='submit'>Далее</button>");
                out.println("</form>");
            }

            printSessionFooter(out, playerName, gamesPlayed);
            out.println("</body></html>");
            return;
        }


        int questionId = (questionIdStr == null) ? 1 : Integer.parseInt(questionIdStr);
        Question question = service.getQuestionById(questionId);

        if (question == null) {
            out.println("<h1>Вопрос не найден</h1>");
        } else if (question.getAnswers().isEmpty()) {
            // Финал
            gamesPlayed++;
            session.setAttribute("gamesPlayed", gamesPlayed);

            out.println("<h2>" + question.getText() + "</h2>");
            out.println("<p><b>Игра завершена.</b></p>");
            out.println("<a href='" + contextPath + "/quest-servlet'>Начать заново</a>");
        } else {

            out.println("<h2>" + question.getText() + "</h2>");
            for (Answer answer : question.getAnswers()) {
                out.println("<form method='get' action='" + contextPath + "/quest-servlet'>");
                out.println("<input type='hidden' name='answerId' value='" + answer.getId() + "'/>");
                out.println("<input type='hidden' name='step' value='1'/>");
                out.println("<button type='submit'>" + answer.getButtonText() + "</button>");
                out.println("</form>");
            }
        }

        printSessionFooter(out, playerName, gamesPlayed);
        out.println("</body></html>");
    }

    // Обработка имени игрока (POST)
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

    // Вывод статистики
    private void printSessionFooter(PrintWriter out, String name, int count) {
        out.println("<hr>");
        out.println("<p>Игрок: <b>" + name + "</b> | Сыграно игр: <b>" + count + "</b></p>");
    }
}