package es.upm.dit.prog.practica4;

import java.util.Arrays;

public class CentroControl {
	private static final int N = 10;
	private Dron[] drones;
	private Mision[] misiones;

	public CentroControl() {
		this.drones = new Dron[N];
		this.misiones = new Mision[N];
	} 
	
	@Override
	public String toString() {
		return "CentroControl [drones=" + Arrays.toString(drones) + ", misiones=" + Arrays.toString(misiones) + "]";
	}



	public void addDron(Dron d) {
		if (d!=null) {
			int count = 0;
			for (int i = 0; i<drones.length; i++) {
				if (drones[i] == null) {
					drones[i] = d;
					count =1;
					break;
				}
			}
			if (count == 0) {
				drones[0] = null;
				for (int j = 1; j<drones.length; j++) {
					drones[j-1] = drones[j];
					drones[j] = null;
				}
				drones[drones.length -1] = d;
			}
		}
	}
	
	public Dron[] getDrones() {
		int cont = 0;
		for (int i = 0; i < drones.length; i++) {
			if (drones[i] != null)  {
				cont++;
			}
		}
		Dron[] lista = new Dron[cont];
		cont = 0;
		for (int i = 0; i < drones.length; i++) {
			if (drones[i] != null)  {
				lista[cont] = drones[i];
				cont++;
			}
		}
		return lista;
	}
	
	public void addMision(Mision m) {
		if (m!=null) {
			int count = 0;
			for (int i = 0; i<drones.length; i++) {
				if (misiones[i] == null) {
					misiones[i] = m;
					count =1;
					break;
				}
			}
			if (count == 0) {
				misiones[0] = null;
				for (int j = 1; j<misiones.length; j++) {
					misiones[j-1] = misiones[j];
					drones[j] = null;
				}
				misiones[misiones.length -1] = m;
			}
		}
	}
	
	/*
	 * public Mision[] getMisiones() { int cont = 0; for (int i = 0; i <
	 * misiones.length; i++) { if (misiones[i] != null) { cont++; } } Mision[] lista
	 * = new Mision[cont]; cont = 0; for (int i = 0; i < drones.length; i++) { if
	 * (misiones[i] != null) { lista[cont] = misiones[i]; cont++; } } return lista;
	 * }
	 */
	
	public Mision[] getMisiones(SelectorMision sm) {
		if (sm == null) {
			sm = new SelectorMisionTrue();
		}
		int num = 0;
		for (int i = 0; i < misiones.length; i++)  {
			if (misiones[i] != null && sm.seleccionar(misiones[i])) {
				num++;
			}
		}
		Mision[] mis = new Mision[num]; 
		num = 0;
		for (int i = 0; i < misiones.length; i++)  {
			if (misiones[i] != null && sm.seleccionar(misiones[i])) {
				mis[num] = misiones[i];
				num++;
			}
		}
		return mis;
	}

	public void update(long t) {
		for (int i = 0; i< drones.length; i++) {
			if (drones[i] != null) {
				drones[i].mover(t);
			}
		}
		
		for (int i = 0; i< misiones.length; i++) {
			if (misiones[i] != null) {
				misiones[i].update(t);
			}
		}
	}


}
