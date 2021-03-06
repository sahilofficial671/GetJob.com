package com.getjob.servlets.job;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getjob.controllers.JobController;
import com.getjob.controllers.UserController;


@WebServlet("/recruiter/jobs")
public class RecruiterListJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobController jobController;
	
    public RecruiterListJob() {
    	jobController = new JobController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!new UserController().checkAuth(request)) {
				response.sendRedirect(request.getContextPath()+"/login");
				return ;
			}
			request.setAttribute("jobs", jobController.getJobs());
			request.getRequestDispatcher("../pages/job/list.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error from: " + this.getClass().getSimpleName() + ", Message: "+ e.getMessage());
		}
	}
}
