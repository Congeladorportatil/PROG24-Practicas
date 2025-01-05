package es.upm.dit.prog.practica5;

import java.util.Objects;

/**
 * @author Victor Vazquez Gonzalez
 * @version 16/02/2024 0:15
 */

public class Posicion {
	/**
	 * Coordenada x en cartesianas.
	 */
	private double x;

	/**
	 * Coordenada y en cartesianas.
	 */

	private double y;

	/**
	 * Coordenada z en cartesianas.
	 */

	private double z;

	/**
	 * Crea un punto en el espacio cartesiano.
	 * 
	 * @param x Coordenada x.
	 * @param y Coordenada y.
	 * @param z Coordenada z.
	 */

	public Posicion(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Devuelve el valor x en cartesianas del punto creado.
	 * 
	 * @return Coordenada x.
	 */

	public double getX() {
		return this.x;
	}

	/**
	 * Devuelve el valor y en cartesianas del punto creado.
	 * 
	 * @return Coordenada y.
	 */

	public double getY() {
		return this.y;
	}

	/**
	 * Devuelve el valor z en cartesianas del punto creado.
	 * 
	 * @return Coordenada z.
	 */

	public double getZ() {
		return this.z;
	}

	/**
	 * Modifica el valor x de un punto en cartesianas.
	 * 
	 * @param x Nueva coordenada x.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Modifica el valor y de un punto en cartesianas.
	 * 
	 * @param y Nueva coordenada y.
	 */

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Modifica el valor z de un punto en cartesianas.
	 * 
	 * @param z Nueva coordenada z.
	 */

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	/**
	 * Evalua si 2 objetos son iguales.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y)
				&& Double.doubleToLongBits(z) == Double.doubleToLongBits(other.z);
	}

	/**
	 * Devuelve un String con informacion de la Posición en cartesianas.
	 */

	@Override
	public String toString() {
		return "Posicion [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	/**
	 * Devuelve el modulo de la distancia entre el origen de coordenadas y el punto
	 * dado.
	 * 
	 * @return Distancia entre el origen y el punto.
	 * 
	 */

	public double modulo() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
	}

	/**
	 * Devuelve la distancia entre un punto (this) y otro pasado por párametro.
	 * 
	 * @param pos Posicion desde donde se quiere calcular la distancia.
	 * @return Distancia entre 2 puntos.
	 * 
	 */

	public double distancia(Posicion pos) {
		double auxx = pos.x - this.x;
		double auxy = pos.y - this.y;
		double auxz = pos.z - this.z;
		return Math.sqrt(Math.pow(auxx, 2) + Math.pow(auxy, 2) + Math.pow(auxz, 2));
	}

}
