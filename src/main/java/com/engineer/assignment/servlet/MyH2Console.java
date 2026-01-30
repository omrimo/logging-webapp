package com.engineer.assignment.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/h2-console/*", initParams = {
    @WebInitParam(name = "webAllowOthers", value = "true")
})
public class MyH2Console extends org.h2.server.web.WebServlet {}