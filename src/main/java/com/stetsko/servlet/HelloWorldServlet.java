package com.stetsko.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet extends HttpServlet {

    private static final String HELLO_WORLD_HTML = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>It's hello world title</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Hello World from Java</h1>\n" +
            "</body>\n" +
            "</html>\n";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(HELLO_WORLD_HTML);
    }
}
