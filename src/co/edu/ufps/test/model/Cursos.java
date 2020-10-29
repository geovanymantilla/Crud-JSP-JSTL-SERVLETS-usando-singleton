package co.edu.ufps.test.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cursos implements Serializable {
	private Integer codigo;
	private String nombre;
	private Integer credito;
	
	
//	public Cursos(Integer codigo, String nombre, Integer credito) {		
//		this.codigo = codigo;
//		this.nombre = nombre;
//		this.credito = credito;
//	}

	
}
