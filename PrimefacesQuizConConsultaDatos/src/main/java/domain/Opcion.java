package domain;
import java.io.Serializable;

public class Opcion implements Serializable {
	private static final long serialVersionUID = 5658716793957904104L;
	private int id;
	private String texto;

	public Opcion(int i, String t) {
		id = i;
		texto = t;
	}
	public int getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
