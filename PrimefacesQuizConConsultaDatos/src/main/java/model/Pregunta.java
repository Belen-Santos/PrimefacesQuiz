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
@Table(name = "pregunta")
public class Pregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	protected int id;

	@Column(name = "texto", nullable = false)
	protected String texto;

	@Column(name = "link", nullable = false)
	protected String link;

	@Column(name = "op1", nullable = false)
	protected String op1;

	@Column(name = "op2", nullable = false)
	protected String op2;

	@Column(name = "op3")
	protected String op3;

	@Column(name = "op4")
	protected String op4;

	@Column(name = "op5")
	protected String op5;

	@Column(name = "correcta", nullable = false)
	protected String correcta;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) private
	 * List<Respuesta> respuestasTodosUsuarios = new ArrayList<Respuesta>();
	 */

	public Pregunta() {

	}

	public Pregunta(String texto, String link, String op1, String op2, String op3, String op4, String op5,
			String correcta) {
		this.texto = texto;
		this.link = link;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.op4 = op4;
		this.op5 = op5;
		this.correcta = correcta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOp1() {
		return op1;
	}

	public void setOp1(String op1) {
		this.op1 = op1;
	}

	public String getOp2() {
		return op2;
	}

	public void setOp2(String op2) {
		this.op2 = op2;
	}

	public String getOp3() {
		return op3;
	}

	public void setOp3(String op3) {
		this.op3 = op3;
	}

	public String getOp4() {
		return op4;
	}

	public void setOp4(String op4) {
		this.op4 = op4;
	}

	public String getOp5() {
		return op5;
	}

	public void setOp5(String op5) {
		this.op5 = op5;
	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

}
