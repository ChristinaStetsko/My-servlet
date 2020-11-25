package com.stetsko.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stetsko.enities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UsersServlet extends HttpServlet {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final List<User> users = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong(1);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(objectMapper.writeValueAsString(users));
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var httpRequestBody = new String(req.getInputStream().readAllBytes());

        var newUser = objectMapper.readValue(httpRequestBody, User.class);

        newUser.setId(idGenerator.getAndIncrement());

        System.out.println("Создаю пользователя: " + newUser.getUsername() + ". User id: " + newUser.getId());
        users.add(newUser);

        resp.getWriter().print(objectMapper.writeValueAsString(newUser));
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}