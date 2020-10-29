package co.edu.ufps.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.ufps.test.model.Cursos;
import co.edu.ufps.test.util.Conexion;

public class CursoDao {
	private Conexion conexion;
	
	private static final String INSERT_CURSOS_SQL = "INSERT INTO cursos (codigo, nombre, credito) VALUES (?, ?, ?);";
    private static final String SELECT_CURSOS_BY_ID = "SELECT * FROM cursos WHERE codigo = ?";
    private static final String SELECT_ALL_CURSOS = "SELECT * FROM cursos";
    private static final String DELETE_CURSOS_SQL = "DELETE FROM cursos WHERE codigo = ?;";
    private static final String UPDATE_CURSOS_SQL = "UPDATE cursos SET codigo = ?,nombre= ?, credito =? WHERE codigo = ?;";

	public CursoDao() {
		
		this.conexion = conexion.getConexion();
	}
	
	public void insert(Cursos curso) throws SQLException {
		System.out.println(INSERT_CURSOS_SQL);
		// try-with-resource statement will auto close the connection.
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_CURSOS_SQL);
			preparedStatement.setInt(1, curso.getCodigo());
			preparedStatement.setString(2, curso.getNombre());
			preparedStatement.setInt(3, curso.getCredito());
			System.out.println(preparedStatement);
			conexion.execute();
		} catch (SQLException e) {
		}

	}
	
	public void delete (int codigo)  throws SQLException{
		try {PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_CURSOS_SQL);
	     preparedStatement.setInt(1, codigo);
	     conexion.execute();
	     }catch(SQLException e){
	    	 
	     }  
	}
	
	public void update(Cursos curso) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_CURSOS_SQL);
			preparedStatement.setInt(1, curso.getCodigo());
			preparedStatement.setString(2, curso.getNombre());
			preparedStatement.setInt(3, curso.getCredito());
			preparedStatement.setInt(4, curso.getCodigo());
			conexion.execute();
		} catch (SQLException e) {
		}

	}
	
	public List<Cursos> selectAllCursos() {
		List <Cursos> cursos =  new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_CURSOS);
			ResultSet rs= conexion.query();
			while(rs.next()) {
				int codigo= rs.getInt("codigo");
				String nombre= rs.getString("nombre");
				int credito= rs.getInt("credito");
				cursos.add(new Cursos(codigo,nombre,credito));
			}
			
		} catch (SQLException e) {
		}
		return cursos;
		
	}
	
	public Cursos select(int cod) {
		Cursos curso= null;
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_CURSOS_BY_ID);
			preparedStatement.setInt(1, cod);
			ResultSet rs= conexion.query();
			while(rs.next()) {
				int codigo= rs.getInt("codigo");
				String nombre= rs.getString("nombre");
				int credito= rs.getInt("credito");
				curso=new Cursos(codigo,nombre,credito);
			}
			
		} catch (SQLException e) {
		}
		return curso;
		
	}
	
	
}
