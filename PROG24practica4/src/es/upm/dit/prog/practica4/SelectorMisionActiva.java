package es.upm.dit.prog.practica4;

public class SelectorMisionActiva implements SelectorMision {
	public boolean seleccionar (Mision m) {
		return m!=null && m.activa();
	}
}
