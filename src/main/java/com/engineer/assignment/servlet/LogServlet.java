package com.engineer.assignment.servlet;



import com.engineer.assignment.dao.LogDao;
import com.engineer.assignment.model.LogMessage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;


@WebServlet("/api/logs")
public class LogServlet extends HttpServlet {


    private final LogDao dao = new LogDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();


        out.print("[");
        boolean first = true;
        for (LogMessage log : dao.findAll()) {
            if (!first) out.print(",");
            out.print("{\"timestamp\":\"" + log.getTimestamp() + "\"," +
                    "\"app\":\"" + log.getApp() + "\"," +
                    "\"message\":\"" + log.getMessage() + "\"}");
            first = false;
        }
        out.print("]");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        dao.insert(new LogMessage(
                Instant.now().getEpochSecond(),
                "EXAMPLE_APP",
                "Generated backend log"
        ));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        dao.clear();
    }
}