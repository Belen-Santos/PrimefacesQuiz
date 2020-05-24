package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dao.JPADao;
import model.Pregunta;
import model.PreguntaOpciones;
import model.Respuesta;
import model.Usuario;

@ManagedBean
//@Named
@SessionScoped
public class PreguntaController {

	@ManagedProperty(value = "#{dao}")
	private JPADao hibernateDao;
	private String opcionSeleccionada = "";
	private PreguntaOpciones preguntaActual;
	private boolean esPasoInicial;
	private boolean esPasoIntermedio;
	private boolean esPasoFinal;
	private int pasoActual = 0;
	private String nombreUsuario;
	private String mensajeFinal;
	private int contadorRespCorrectas = 0;
	private String textoBoton;
	private List<Pregunta> preguntasHibernate;
	private Usuario usuario;
	private List<String> respuestas;
	private String nombreOtroUsuario;

	public PreguntaController() {
	}

	@PostConstruct
	public void init() {
		preguntasHibernate = hibernateDao.getPreguntas();
		preguntaActual = new PreguntaOpciones(preguntasHibernate.get(0));
		respuestas = new ArrayList<String>();
		esPasoInicial = true;
		setTextoBoton("Siguiente");
	}

	public void siguientePaso() {
		/*
		 * if (pasoActual == preguntasHibernate.size() - 1) {
		 * setTextoBoton("Nuevo Cuestionario"); }
		 */
		if (esPasoFinal) {
			esPasoInicial = true;
			esPasoFinal = false;
			nombreUsuario = "";
			pasoActual = 0;
			preguntaActual = new PreguntaOpciones(preguntasHibernate.get(0));
			respuestas = new ArrayList<String>();
			contadorRespCorrectas = 0;
			setTextoBoton("Siguiente");
			return;
		}
		if (esPasoInicial && !nombreUsuario.equals("")) {
			esPasoInicial = false;
			esPasoIntermedio = true;
			usuario = hibernateDao.saveUsuario(nombreUsuario);
			return;
		}
		if (!opcionSeleccionada.equals("")) {
			preguntaActual = new PreguntaOpciones(preguntasHibernate.get(pasoActual));
			if (opcionSeleccionada.equals(preguntaActual.getCorrecta())) {
				contadorRespCorrectas++;
			}
			hibernateDao.saveRespuesta(opcionSeleccionada, usuario, preguntaActual);
			respuestas.add(opcionSeleccionada);
			preguntasHibernate = hibernateDao.getPreguntas();
			pasoActual++;
			if (pasoActual == preguntasHibernate.size()) {
				esPasoFinal = true;
				List<Respuesta> respuestasUsu = hibernateDao.getRespuestasUsuario(usuario);
				respuestas = new ArrayList<>();
				for (Respuesta resp : respuestasUsu) {
					respuestas.add(resp.getTexto());
				}
				esPasoIntermedio = false;
				opcionSeleccionada = "";
				setTextoBoton("Nuevo Cuestionario");
				return;
			}
			preguntaActual = new PreguntaOpciones(preguntasHibernate.get(pasoActual));
			opcionSeleccionada = "";
		}
	}

	public void consultarRespuestasOtroUsuario() {
		Usuario otroUsuario = hibernateDao.getUsuario(nombreOtroUsuario);
		if (otroUsuario != null) {
			nombreUsuario = nombreOtroUsuario;
			respuestas = new ArrayList<>();
			List<Respuesta> respuestasUsu = hibernateDao.getRespuestasUsuario(otroUsuario);
			if (respuestasUsu.isEmpty()) {
				System.out
						.println("No se encontraron respuestas en base de datos para el usuario " + nombreOtroUsuario);
			} else {
				contadorRespCorrectas = 0;
				System.out.println("Contador inicial:" + contadorRespCorrectas);
				for (Respuesta resp : respuestasUsu) {
					System.out.println("respuesta:" + resp.getTexto());
					Pregunta p = hibernateDao.getPregunta(resp.getPregunta());
					if (p.getCorrecta().equals(resp.getTexto())) {
						contadorRespCorrectas++;
					}
					respuestas.add(resp.getTexto());
					System.out.println("Contador:" + contadorRespCorrectas);
				}
				mensajeFinal = nombreUsuario + ", ha obtenido " + contadorRespCorrectas
						+ " respuestas correctas en este cuestionario.";
			}
		} else {
			System.out.println("Usuario " + nombreOtroUsuario + " no existe en base de datos");
		}
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

	public PreguntaOpciones getPreguntaActual() {
		return preguntaActual;
	}

	public void setPreguntaActual(PreguntaOpciones preguntaActual) {
		this.preguntaActual = preguntaActual;
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
		mensajeFinal = nombreUsuario + ", ha obtenido " + contadorRespCorrectas
				+ " respuestas correctas en este cuestionario.";
		return mensajeFinal;
	}

	public void setMensajeFinal(String mensajeFinal) {
		this.mensajeFinal = mensajeFinal;
	}

	public String getTextoBoton() {
		return textoBoton;
	}

	public void setTextoBoton(String textoBoton) {
		this.textoBoton = textoBoton;
	}

	public List<Pregunta> getPreguntasHibernate() {
		return preguntasHibernate;
	}

	public void setPreguntasHibernate(List<Pregunta> preguntasHibernate) {
		this.preguntasHibernate = preguntasHibernate;
	}

	public void setHibernateDao(JPADao hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public String getNombreOtroUsuario() {
		return nombreOtroUsuario;
	}

	public void setNombreOtroUsuario(String nombreOtroUsuario) {
		this.nombreOtroUsuario = nombreOtroUsuario;
	}

}
