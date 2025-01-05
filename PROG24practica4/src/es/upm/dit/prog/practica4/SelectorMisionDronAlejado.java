package es.upm.dit.prog.practica4;

public class SelectorMisionDronAlejado implements SelectorMision {
	
	private Posicion p;
	private double d;
	public SelectorMisionDronAlejado(Posicion p, double d) {
		this.p = p;
		this.d = d;
	}


	public boolean seleccionar(Mision m) {
		// TODO Auto-generated method stub
		return m != null && m.getDron().getPos().distancia(p) > d;
	}

}
