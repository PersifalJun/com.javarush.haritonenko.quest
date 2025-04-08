package org.example.quest.controller;

import org.example.quest.service.QuestionAndAnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class QuestServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private QuestServlet servlet = new QuestServlet();

    @Test
     void init() {
        servlet.init();
        assertNotNull(servlet.getService());
        assertEquals(QuestionAndAnswerService.class, servlet.getService().getClass());

    }

    @BeforeEach
    void setUp() {

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
    }

    @Test
    void shouldForwardToWelcomeJspWhenNoPlayerName() throws ServletException, IOException {
        when(session.getAttribute("playerName")).thenReturn(null);
        when(session.getAttribute("gamesPlayed")).thenReturn(null);
        when(request.getRequestDispatcher("/welcome.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request).getRequestDispatcher("/welcome.jsp");
        verify(dispatcher).forward(request, response);
    }
    @Test
    void shouldShowFirstQuestionIfPlayerExists() throws ServletException, IOException {
        when(session.getAttribute("playerName")).thenReturn("Игорь");
        when(session.getAttribute("gamesPlayed")).thenReturn(3);
        when(request.getParameter("id")).thenReturn(null);
        when(request.getParameter("answerId")).thenReturn(null);
        when(request.getRequestDispatcher("/quest.jsp")).thenReturn(dispatcher);

        servlet = new QuestServlet() {
            @Override
            public void init() {
                this.service = new QuestionAndAnswerService();
            }
        };
        servlet.init();
        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/quest.jsp");
        verify(dispatcher).forward(request, response);
    }
    @Test
    void shouldForwardToActionWhenAnswerChosen() throws ServletException, IOException {
        when(session.getAttribute("playerName")).thenReturn("Евгений");
        when(session.getAttribute("gamesPlayed")).thenReturn(2);
        when(request.getParameter("answerId")).thenReturn("2");
        when(request.getParameter("step")).thenReturn("1");
        when(request.getRequestDispatcher("/action.jsp")).thenReturn(dispatcher);


        servlet = new QuestServlet() {
            @Override
            public void init() {
                this.service = new QuestionAndAnswerService();
            }
        };
        servlet.init();

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/action.jsp");
        verify(dispatcher).forward(request, response);
    }
    @Test
    void shouldSaveNameToSessionAndRedirect() throws IOException {
        when(request.getParameter("playerName")).thenReturn("Игорь");

        servlet.doPost(request, response);

        verify(session).setAttribute("playerName", "Игорь");
        verify(response).sendRedirect("/quest-servlet");
    }

    @Test
    void shouldRedirectWithoutSavingNameIfBlank() throws IOException {
        when(request.getParameter("playerName")).thenReturn("  ");

        servlet.doPost(request, response);

        verify(session, never()).setAttribute(eq("playerName"), any());
        verify(response).sendRedirect("/quest-servlet");
    }

    @Test
    void shouldRedirectEvenIfNameIsNull() throws IOException {
        when(request.getParameter("playerName")).thenReturn(null);

        servlet.doPost(request, response);

        verify(session, never()).setAttribute(eq("playerName"), any());
        verify(response).sendRedirect("/quest-servlet");
    }


}