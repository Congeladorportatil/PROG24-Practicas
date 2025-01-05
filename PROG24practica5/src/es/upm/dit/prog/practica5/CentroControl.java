package es.upm.dit.prog.practica5;

import java.util.List;
import java.util.ArrayList;

public class CentroControl {
	
	private List<Dron> drones;
	private List<Mision> misiones;
	
	public CentroControl() {
		this.drones = new ArrayList<Dron>();
		this.misiones = new ArrayList<Mision>();
	}

	@Override
	public String toString() {
		return "CentroControl [drones=" + drones + ", misiones=" + misiones + "]";
	}
	
	
	
	public void addDron(Dron d) {
		if (d != null)
			this.drones.add(d);
	}
	
	public void addMision(Mision m) {
		if (m != null) {
			this.misiones.add(m);
		}
	}
	
	public List<Dron> getDrones() {
		return new ArrayList<>(this.drones);
	}
	
	public List<Mision> getMisiones(SelectorMision sm) {
		if (sm == null) {
			sm = new SelectorMisionTrue();
		}
		
		List<Mision> validas = new ArrayList<Mision>();
		
		for (Mision mision : misiones) {
			if (sm.seleccionar(mision)) {
				validas.add(mision);
			}
		}
		
		return validas;
	}
	
	public void update(long t) {
		for (Dron dron : drones) {
			dron.mover(t);
		}
		
		for (Mision mision : misiones) {
			mision.update(t);
		}
	}
	
}
