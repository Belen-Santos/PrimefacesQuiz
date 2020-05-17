package domain;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Pregunta implements Serializable {
	private static final long serialVersionUID = 5658716793957904104L;
	private int id;
	private String texto;
	private String link;
	private List<Opcion> opciones;
	private int resp;
	private int correcta;

	public Pregunta() {
	        opciones = new ArrayList<Opcion>();
		this.texto = "";
		this.resp = 0;
	}

	public Pregunta(int id, String texto, String link, List<Opcion> opciones, int correcta) {
		this.id = id;
		this.texto = texto;
		this.link = link;
		this.opciones = opciones;
		this.correcta = correcta;
		this.resp = 0;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}

	public int getResp() {
		return this.resp;
	}

	public void setResp(int resp) {
		this.resp = resp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public int getCorrecta() {
		return correcta;
	}

	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}

}
