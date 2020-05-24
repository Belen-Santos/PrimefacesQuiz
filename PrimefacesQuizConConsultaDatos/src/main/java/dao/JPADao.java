package dao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Pregunta;
import model.Respuesta;
import model.Usuario;

@ManagedBean(name = "dao")
@SessionScoped
public class JPADao {
	private static EntityManager em;

	public JPADao() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		em = entityManagerFactory.createEntityManager();
	}

	public void saveRespuesta(String textoRespuesta, Usuario usu, Pregunta preg) {
		em.getTransaction().begin();
		// Respuesta resp = new Respuesta(textoRespuesta, usu, preg);
		Respuesta resp = new Respuesta(textoRespuesta, usu.getId(), preg.getId());
		em.persist(resp);
		em.getTransaction().commit();
	}

	/**
	 * esta consulta devuelve el objeto usuario almacenado en base de datos dado el
	 * nombre
	 * 
	 * @param nombreUsu
	 * @return
	 */
	public Usuario getUsuario(String nombreUsu) {
		Usuario usuario = null;

		List<Usuario> usuariosNombre = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = ?1")
				.setParameter(1, nombreUsu).getResultList();
		if (usuariosNombre != null && !usuariosNombre.isEmpty()) {
			usuario = usuariosNombre.get(0);
		}

		return usuario;
	}

	public Usuario saveUsuario(String nombreUsu) {
		List<Usuario> usuariosNombre = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = ?1")
				.setParameter(1, nombreUsu).getResultList();
		if (usuariosNombre != null && !usuariosNombre.isEmpty()) {
			return usuariosNombre.get(0);
		} else {
			em.getTransaction().begin();
			Usuario nuevoUsuario = new Usuario(nombreUsu);
			em.persist(nuevoUsuario);
			em.getTransaction().commit();
			return nuevoUsuario;
		}

	}

	public List<Pregunta> getPreguntas() {
		Query q = em.createQuery("SELECT p FROM Pregunta p");
		List<Pregunta> preguntaList = q.getResultList();
		return preguntaList;
	}

	public Pregunta getPregunta(int idPregunta) {
		Pregunta p = em.find(Pregunta.class, idPregunta);
		return p;
	}

	public List<Respuesta> getRespuestasUsuario(Usuario usu) {
		Query q = em.createQuery("SELECT r FROM Respuesta r WHERE r.usuario = " + usu.getId());
		List<Respuesta> respuestaList = q.getResultList();
		return respuestaList;
	}
}
