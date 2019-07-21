package kz.zhanbolat.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.action.Action;
import kz.zhanbolat.web.action.ActionFactory;

@WebServlet(urlPatterns="main")
public class MainController extends HttpServlet {
	private static Logger logger = LogManager.getLogger(MainController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		processRequest(req, resp);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		Action action = ActionFactory.defineAction(req);
		try {
			action.performe(req, resp);
		} catch (IOException e) {
			logger.error("Error on performing the action.", e);
		}
	}
	
}
