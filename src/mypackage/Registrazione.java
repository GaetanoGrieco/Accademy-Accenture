package mypackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.*;

import java.sql.*;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String un = request.getParameter("uname");
		String pa = request.getParameter("pass");
		String no = request.getParameter("nome");
		String co = request.getParameter("cognome");
		
//		if (co.length() + em.length() + un.length() + pa.length() + no.length() == 0) {
//			RequestDispatcher rd3 = request.getRequestDispatcher("form.jsp");
//			String messaggio = "dati non presenti";
//			request.setAttribute("messaggio", messaggio);
//			rd3.forward(request, response);
//		} else {
		
			Connection conn = Connessione.getCon(); // abbiamo stabilito una connessione ad db
			try {
				String query = " insert into studente (matricola, username, password= AES_ENCRYPT(?,'key')), nome, cognome)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				String query1 = "select count(*) from studente";
				PreparedStatement st = conn.prepareStatement(query1);
				ResultSet rs = st.executeQuery();
				int n = 0;
				while (rs.next()) {
					n = rs.getInt("count(*)");
				}
				preparedStmt.setInt(1, n + 1000);
				preparedStmt.setString(2, un);
				preparedStmt.setString(3, pa);
				preparedStmt.setString(4, no);
				preparedStmt.setString(5, co);
				// execute the preparedstatement
				preparedStmt.executeUpdate();
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.write("<h2> Registrazione avvenuta con successo.. <h2> <br>");
				pw.write("<h3> Username: " + un + " </h3>");
				pw.write("<h3> Password: " + pa + " </h3>");
				pw.write("<h3> Nome: " + no + " </h3>");
				pw.write("<h3> Cognome: " + co + " </h3>");
				response.addHeader("REFRESH", "5;URL=index.jsp");

			} catch (SQLException e) {

				System.out.println(e.getMessage());
			}
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}