	package es.upm.dit.prog.practica2;
	
	import java.util.Arrays;
	import java.util.Objects;
	
	public class Mision {
		private String id;
		private Dron dron;
		private Posicion[] posiciones;
		private long[] tiempos;
		private int nPosiciones;
		private int posicion;
	
		public Mision(String id, Dron dron, int maxPosiciones) {
			this.id = id;
			this.dron = dron;
			this.posiciones = new Posicion[maxPosiciones];
			this.tiempos = new long[maxPosiciones];
			this.nPosiciones = 0;
			this.posicion = 0;
		}
	
		public String getId() {
			return this.id;
		}
	
		public Dron getDron() {
			return this.dron;
		}
	
		public Posicion[] getPosiciones() {
			return this.posiciones;
		}
	
		public long[] getTiempos() {
			return this.tiempos;
		}
	
		public int getnPosiciones() {
			return this.nPosiciones;
		}
	
		public int getPosicion() {
			return this.posicion;
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Mision other = (Mision) obj;
			return Objects.equals(id, other.id);
		}
	
		@Override
		public String toString() {
			return "Mision [id=" + id + ", dron=" + dron + ", posiciones=" + Arrays.toString(posiciones) + ", tiempos="
					+ Arrays.toString(tiempos) + ", nPosiciones=" + nPosiciones + ", posicion=" + posicion + "]";
		}
	
		
		public void addPosT(Posicion pos, long t) {
			if (pos != null && this.nPosiciones < this.posiciones.length) {
				this.posiciones[this.nPosiciones] = pos;
				this.tiempos[this.nPosiciones] = t;
				this.nPosiciones++;
			}
		}
	
		public boolean activa() {
			return this.posicion < this.nPosiciones && this.posiciones[this.posicion] != null;
		}
	
		public void update(long t) {
			if (activa() && dron.getT() == t && tiempos[posicion] == t ) {
				Posicion vel = new  Posicion(0,0,0);
				if (posicion < nPosiciones -1) {
					long diff = tiempos[posicion+1] - tiempos[posicion];
					if (t>=0) {
						double velx = (posiciones[posicion+1].getX() - posiciones[posicion].getX())/diff;
						double vely = (posiciones[posicion+1].getY() - posiciones[posicion].getY())/diff;
						double velz = (posiciones[posicion+1].getZ() - posiciones[posicion].getZ())/diff;
						vel = new Posicion(velx,vely,velz);
					} 
				}
				dron.setVel(vel);
				posicion++;
			}
		}
	}
	
	
