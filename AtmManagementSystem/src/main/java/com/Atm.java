/*
 Title:ATM Management System
 Author: Alagu Vignesh K
 created on:10-01-2023
 updated on:05-04-2023
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Atm
 */
public class Atm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");

		String pinnumber = request.getParameter("pinnumber");

		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = null;

		try {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Atmmachine","root","alagu@k");

		PreparedStatement preparedstatement = connection.prepareStatement("Select*from atm where username=? and pinnumber=?");

		preparedstatement.setString(1, username);

		preparedstatement.setString(2, pinnumber);


		ResultSet resultset = preparedstatement.executeQuery();

		if(resultset.next()) {

		session.setAttribute("username", resultset.getString("username"));

		dispatcher = request.getRequestDispatcher("Menu.jsp");

		}else {

		request.setAttribute("status", "failed");

		dispatcher=request.getRequestDispatcher("AtmLogin.html");

		}

		dispatcher.forward(request, response);

		}catch(Exception exception) {

		exception.printStackTrace();

		}



		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
