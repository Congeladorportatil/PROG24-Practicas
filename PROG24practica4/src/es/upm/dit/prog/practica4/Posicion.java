package es.upm.dit.prog.practica4;

import java.util.Objects;

public class Posicion {

	private double x;
	private double y;
	private double z;
	
	public Posicion() {
		x = 0.00;
		y = 0.00;
		z = 0.00;
	}
	
	public Posicion(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;	
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getZ() {
		return this.z;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
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
	
	
	@Override
	public String toString() {
		return "Posicion [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public double modulo() {
		return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y, 2) + Math.pow(this.z,2));
	}
	public double distancia(Posicion pos) {
		double auxx = pos.x - this.x;
		double auxy = pos.y - this.y;
		double auxz = pos.z - this.z;
		return Math.sqrt(Math.pow(auxx,2)+Math.pow(auxy, 2) + Math.pow(auxz,2));
	}
	

}

