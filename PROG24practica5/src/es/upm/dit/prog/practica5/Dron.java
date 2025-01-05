package es.upm.dit.prog.practica5;

import java.util.Objects;

/**
 * @author Víctor Vázquez González
 * @version 16/02/2024 0:15
 * @see Posicion
 */

public class Dron {

	/**
	 * Constante que aplica la distancia de seguridad entre drones. Se trata de la
	 * mínima distancia a la que pueden acercarse.
	 */

	private static final long SAFETY_DISTANCE = 2;

	/**
	 * ID del dron. Es único.
	 */

	private String id;

	/**
	 * 
	 * Posición del dron.
	 * 
	 */

	private Posicion pos;

	/**
	 * El valor de tiempo en el que se ha visto por última vez
	 */

	private long t;

	/**
	 * Velocidad del Dron.
	 */

	private Posicion vel;

	/**
	 * Crea un dron con un identificador, una posición determinada, un tiempo y una
	 * velocidad inicial.
	 * 
	 * @param id  Identificador único del dron.
	 * @param pos Posición del Dron.
	 * @param t   Último tiempo almacenado.
	 * @param vel Velocidad del Dron.
	 */

	public Dron(String id, Posicion pos, long t, Posicion vel) {
		this.id = id;
		this.pos = pos;
		this.t = t;
		this.vel = vel;
	}

	/**
	 * Devuelve un String con el identificador.
	 * 
	 * @return Identificador.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Devuelve la Posición del Dron.
	 * 
	 * @return Posición.
	 */

	public Posicion getPos() {
		return pos;
	}

	/**
	 * Devuelve el último tiempo del dron.
	 * 
	 * @return último tiempo del dron.
	 */

	public long getT() {
		return t;
	}

	/**
	 * Devuelve la velocidad actual del dron.
	 * 
	 * @return velocidad del dron.
	 */

	public Posicion getVel() {
		return vel;
	}

	/**
	 * Modifica el id del dron.
	 * 
	 * @param id Nuevo identificador nuevo del dron.
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Modifica la Posición del dron.
	 * 
	 * @param pos Nueva posición del dron.
	 */

	public void setPos(Posicion pos) {
		this.pos = pos;
	}

	/**
	 * Modifica el último tiempo del dron.
	 * 
	 * @param t Nuevo tiempo del dron.
	 */

	public void setT(long t) {
		this.t = t;
	}

	/**
	 * Modifica la velocidad del dron.
	 * 
	 * @param vel Nueva velocidad del dron.
	 */

	public void setVel(Posicion vel) {
		this.vel = vel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Evalúa si 2 objetos son iguales.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dron other = (Dron) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Devuelve un String con la información del dron.
	 */

	@Override
	public String toString() {
		return "Dron [id=" + id + ", pos=" + pos + ", t=" + t + ", vel=" + vel + "]";
	}

	/**
	 * Emula el movimiento del dron entre el tiempo del dron y el del párametro,
	 * mediante la velocidad y la posición, siguiendo las ecuaciones de Cinématica
	 * (x = x_o + v(t_2 - t_1)
	 * 
	 * @param t Nuevo último tiempo hasta donde se mueve el dron.
	 */

	public void mover(long t) {
		if (t >= this.t) {
			double velX = vel.getX();
			double velY = vel.getY();
			double velZ = vel.getZ();

			double posX = pos.getX() + (velX * (t - this.t));
			double posY = pos.getY() + (velY * (t - this.t));
			double posZ = pos.getZ() + (velZ * (t - this.t));

			pos = new Posicion(posX, posY, posZ);
			this.t = t;
		}
	}

	/**
	 * Verifica si el dron, y otro pasado por parámetro están a una distancia menor
	 * de la de seguridad.
	 * 
	 * @param otro Dron desde donde se verifica si su posición supone un peligro.
	 * @return true si se encuentran a una distancia menor a la de seguridad (está
	 *         en peligro) y false si no es así.
	 * 
	 */

	public boolean peligro(Dron otro) {
		if (otro != null && !this.equals(otro)) {
			double distanciaActual = this.pos.distancia(otro.getPos());
			return distanciaActual < SAFETY_DISTANCE;
		}
		return false;
	}

}
