package com.liemartt.servlet;

import com.liemartt.service.MatchScoreCalculationService;
import com.liemartt.service.OngoingMatchesService;
import com.liemartt.service.handler_chain.ChainBuilder;
import com.liemartt.util.ThymeleafUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {
    protected final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(ChainBuilder.buildChain());
    protected ServletContext servletContext;
    protected TemplateEngine templateEngine;
    protected WebContext context;

    protected void initializeServlet(HttpServletRequest req, HttpServletResponse resp) {
        servletContext = getServletContext();
        templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        context = ThymeleafUtil.getWebContext(req, resp, servletContext);
    }

}
