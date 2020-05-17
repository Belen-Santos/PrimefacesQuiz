package service;

import java.util.ArrayList;
import java.util.List;

import domain.Opcion;
import domain.Pregunta;

import redis.clients.jedis.Jedis;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pregS")
@SessionScoped
public class PreguntaServiceImpl {

	/*
	 * this implementation is not thread-safe
	 */
	private Jedis conn = new Jedis("localhost");
	private List<Pregunta> preguntas;

	public PreguntaServiceImpl() {
		preguntas = new ArrayList<Pregunta>();
		conn.select(9);
		preguntas = new ArrayList<Pregunta>();
		for (String st : conn.smembers("quiz:set")) {
			String texto = conn.hget("quiz:hash:" + st, "texto");
			String link = conn.hget("quiz:hash:" + st, "link");
			List<Opcion> opciones = new ArrayList<Opcion>();
			for(int i = 1; i <=  conn.hlen("quiz:hash:" + st) - 3; i++) {
			  opciones.add(new Opcion(i, conn.hget("quiz:hash:" + st, "op" + Integer.toString(i))));
                        }
			String well = conn.hget("quiz:hash:" + st, "well");
			preguntas.add(new Pregunta(Integer.parseInt(st), texto, link, opciones, Integer.parseInt(well)));
		}
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public Pregunta getPregunta(int id) {
		if (id <= preguntas.size()) {
			return preguntas.get(id - 1);
		}
		return null;
	}

	public void setRespuesta(int i, int j) {
		preguntas.get(i).setResp(j);
	}

}
