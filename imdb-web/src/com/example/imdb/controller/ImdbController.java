package com.example.imdb.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.imdb.service.MovieService;

/**
 * Servlet implementation class ImdbController
 */
@WebServlet(urlPatterns = "/search")
public class ImdbController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Inject
    private MovieService movieService;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var fromYear = 1970;
		var toYear = 1979;
		var genre = request.getParameter("genre");
		// validation
		try {
			fromYear = Integer.parseInt(request.getParameter("fromYear"));
		} catch (NumberFormatException e) {
		}
		try {
			toYear = Integer.parseInt(request.getParameter("toYear"));
		} catch (NumberFormatException e) {
		}
		//delegation
		var movies = movieService.findAllMoviesByYearRangeAndGenre(genre, fromYear, toYear);
		// model -> JSP
		request.setAttribute("movies", movies); // JSP: ${movies}
		// dispatch
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
