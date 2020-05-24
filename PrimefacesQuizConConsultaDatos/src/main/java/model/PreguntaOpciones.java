package model;

import java.util.ArrayList;
import java.util.List;

import domain.Opcion;

public class PreguntaOpciones extends Pregunta {

	private List<Opcion> opciones;

	public PreguntaOpciones(String texto, String link, String op1, String op2, String op3, String op4, String op5,
			String correcta) {
		super(texto, link, op1, op2, op3, op4, op5, correcta);

	}

	public PreguntaOpciones(Pregunta pregunta) {
		this.id = pregunta.id;
		this.texto = pregunta.texto;
		this.link = pregunta.link;
		this.op1 = pregunta.op1;
		this.op2 = pregunta.op2;
		this.op3 = pregunta.op3;
		this.op4 = pregunta.op4;
		this.op5 = pregunta.op5;
		this.correcta = pregunta.correcta;
		this.opciones = getOpciones();
	}

	public List<Opcion> getOpciones() {
		List<Opcion> listaOpciones = new ArrayList<Opcion>();
		if (op1 != null) {
			listaOpciones.add(new Opcion(1, op1));
		}
		if (op2 != null) {
			listaOpciones.add(new Opcion(2, op2));
		}
		if (op3 != null) {
			listaOpciones.add(new Opcion(3, op3));
		}
		if (op4 != null) {
			listaOpciones.add(new Opcion(4, op4));
		}
		if (op5 != null) {
			listaOpciones.add(new Opcion(5, op5));
		}

		return listaOpciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

}
