package co.edu.ufps.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.test.dao.CursoDao;
import co.edu.ufps.test.model.Cursos;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoDao cursoDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		this.cursoDao= new CursoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getServletPath();
		try {
		switch (action) {
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
				insertarCurso(request,response);
				break;
			case "/delete":
				deleteCurso(request,response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCurso(request,response);
				break;
			default:
				listCursos(request,response);
				break;
		}
		}catch(SQLException e){
			throw new ServletException(e);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("cursoForm.jsp");
		dispatcher.forward(request, response);
		
	}
	private void insertarCurso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String nombre = request.getParameter("nombre");
		int credito = Integer.parseInt(request.getParameter("credito"));
		Cursos curso = new Cursos(codigo,nombre,credito);
		
		cursoDao.insert(curso);
		response.sendRedirect("list");			
	}
	
	private void listCursos(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < Cursos > listCursos = cursoDao.selectAllCursos();
		        request.setAttribute("listCursos", listCursos);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("cursoList.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
		        int codigo = Integer.parseInt(request.getParameter("codigo"));
		        Cursos existingCurso = cursoDao.select(codigo);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("cursoForm.jsp");
		        request.setAttribute("curso", existingCurso);
		        dispatcher.forward(request, response);

		    }
	
	private void updateCurso(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int codigo = Integer.parseInt(request.getParameter("codigo"));
		        String nombre = request.getParameter("nombre");
		        int credito = Integer.parseInt(request.getParameter("credito"));
		        
		        Cursos cur = new Cursos(codigo,nombre,credito);
		        cursoDao.update(cur);
		        response.sendRedirect("list");
		    }

	private void deleteCurso(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int codigo = Integer.parseInt(request.getParameter("codigo"));
		        cursoDao.delete(codigo);
		        response.sendRedirect("list");
	}

		    

}
