package es.upm.dit.prog.practica5;

public class SelectorMisionDronAterrizando implements SelectorMision {


	@Override
	public boolean seleccionar(Mision m) {
		if ( m != null) {
			Posicion vel = m.getDron().getVel();
			return vel.getX() < 0.2 && vel.getY() < 0.2 && vel.getZ() < 0;
		}
		return false;
		
	}

}
