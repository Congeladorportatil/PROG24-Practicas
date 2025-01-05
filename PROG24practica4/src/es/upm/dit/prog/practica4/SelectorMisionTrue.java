package es.upm.dit.prog.practica4;

public class SelectorMisionTrue implements SelectorMision {
	public boolean seleccionar (Mision m) {
		return m != null;
	}
}
