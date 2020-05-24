package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "respuesta")
public class Respuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;

	@Column(name = "idPregunta", nullable = false)
	//private Pregunta pregunta;
	private int pregunta;

	@Column(name = "idUsuario", nullable = false)
	//private Usuario usuario;
	private int usuario;


	@Column(name = "texto", nullable = false)
	private String texto;

	public Respuesta() {
		// Constructor por defecto
	}

	//public Respuesta(String txt, Usuario usu, Pregunta preg) {
	public Respuesta(String txt, int usu, int preg) {
		this.texto = txt;
		this.usuario = usu;
		this.pregunta = preg;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the usuario
	 */
	//public Usuario getUsuario() {
	public int getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(int usuario) {
	//public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the pregunta
	 */
	//public Pregunta getPregunta() {
	public int getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(int pregunta) {
	//public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
