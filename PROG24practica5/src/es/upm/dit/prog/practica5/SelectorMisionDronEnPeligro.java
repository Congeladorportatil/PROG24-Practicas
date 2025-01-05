package es.upm.dit.prog.practica5;

public class SelectorMisionDronEnPeligro implements SelectorMision {

	private Dron[] dr;
	public SelectorMisionDronEnPeligro(Dron[] dr) {
		this.dr = dr;
	}
	
	@Override
	public boolean seleccionar(Mision m) {
		for (int i = 0; i < dr.length; i++) {
			if (dr[i] != null && dr[i].peligro(m.getDron())) {
				return true;
			}
		}
		return false;
	}

}
