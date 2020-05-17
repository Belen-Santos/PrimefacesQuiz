package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import domain.Pregunta;
import service.PreguntaServiceImpl;

@ManagedBean
@SessionScoped
public class PreguntaController {

	private String opcionSeleccionada = "";
	private Pregunta preguntaActual;
	private List<Pregunta> preguntas;
	private boolean esPasoInicial;
	private boolean esPasoIntermedio;
	private boolean esPasoFinal;
	private int pasoActual = 0;
	private String nombreUsuario;
	private String mensajeFinal;
	private int contadorRespCorrectas = 0;

	@ManagedProperty(value = "#{pregS}")
	private PreguntaServiceImpl pregService;

	@PostConstruct
	public void init() {
		preguntas = pregService.getPreguntas();
		preguntaActual = preguntas.get(0);
		esPasoInicial = true;
	}

	public void setPregService(PreguntaServiceImpl p) {
		this.pregService = p;
	}

	public void siguientePaso() {
               if(esPasoFinal) {
		       esPasoInicial=true;
		       esPasoFinal=false;
		       nombreUsuario="";
		       pasoActual=0;
		       preguntaActual = preguntas.get(0);
	               contadorRespCorrectas = 0;
		       return;
               }
               if (esPasoInicial && !nombreUsuario.equals("")) {
		       esPasoInicial=false;
		       esPasoIntermedio = true;
		       return;
	       }
	       if (!opcionSeleccionada.equals("") ) {
	  			int valor = Integer.valueOf(opcionSeleccionada);
				preguntaActual = preguntas.get(pasoActual);
	  			if (Integer.valueOf(opcionSeleccionada) == preguntaActual.getCorrecta()) {
					contadorRespCorrectas++;
				}
				pregService.setRespuesta(pasoActual, valor);
			        pasoActual++;
				if(pasoActual == preguntas.size()) {
				   esPasoFinal = true;
				   esPasoIntermedio = false;
				   opcionSeleccionada="";
				   return;
                                }
		                preguntaActual = preguntas.get(pasoActual);
				opcionSeleccionada = "";
	       }
	}

	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

	public Pregunta getPreguntaActual() {
		return preguntaActual;
	}

	public void setPreguntaActual(Pregunta preguntaActual) {
		this.preguntaActual = preguntaActual;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public boolean isEsPasoInicial() {
		return esPasoInicial;
	}

	public void setEsPasoInicial(boolean esPasoInicial) {
		this.esPasoInicial = esPasoInicial;
	}

	public boolean isEsPasoIntermedio() {
		return esPasoIntermedio;
	}

	public void setEsPasoIntermedio(boolean esPasoIntermedio) {
		this.esPasoIntermedio = esPasoIntermedio;
	}

	public boolean isEsPasoFinal() {
		return esPasoFinal;
	}

	public void setEsPasoFinal(boolean esPasoFinal) {
		this.esPasoFinal = esPasoFinal;
	}

	public int getPasoActual() {
		return pasoActual;
	}

	public void setPasoActual(int pasoActual) {
		this.pasoActual = pasoActual;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getMensajeFinal() {
		mensajeFinal = nombreUsuario + ", ha obtenido " + contadorRespCorrectas + " respuestas correctas.";
		return mensajeFinal;
	}

	public void setMensajeFinal(String mensajeFinal) {
		this.mensajeFinal = mensajeFinal;
	}

}
