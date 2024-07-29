package com.liemartt.servlet;

import com.liemartt.util.ThymeleafUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {
    protected ServletContext servletContext;
    protected TemplateEngine templateEngine;
    protected WebContext context;

    protected void initializeServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletContext = getServletContext();
        templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        context = ThymeleafUtil.getWebContext(req, resp, servletContext);
    }
}
