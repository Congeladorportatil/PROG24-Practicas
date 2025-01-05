	package es.upm.dit.prog.practica5;
	
	import java.util.Arrays;
	import java.util.Objects;
	
	/**
	 * @author Victor Vazquez Gonzalez
	 * @version 10/03/2024 18:53
	 * @see Posicion, Dron
	 */
	
	public class Mision {
		
		/**
		 * Identificador de la mision
		 */
		
		private String id;
		
		/**
		 * Dron de la mision
		 */
		
		private Dron dron;
		
		/**
		 * Array de posiciones que sigue la mision
		 */
		
		private Posicion[] posiciones;
		
		/**
		 * Array de tiempos que sigue la mision
		 */
		
		private long[] tiempos;
		
		/**
		 * Numero de posiciones de la mision
		 */
		
		private int nPosiciones;
		
		/**
		 * Posicion en el array
		 */
		
		private int posicion;
	
		
		/**
		 * Mision del dron
		 * @param id Identificador de la mision
		 * @param dron Dron que efectua la mision
		 * @param maxPosiciones Numero de casillas del array de posiciones de la mision
		 */
		
		public Mision(String id, Dron dron, int maxPosiciones) {
			this.id = id;
			this.dron = dron;
			this.posiciones = new Posicion[maxPosiciones];
			this.tiempos = new long[maxPosiciones];
			this.nPosiciones = 0;
			this.posicion = 0;
		}
	
		/**
		 * Devuelve el Identificador
		 * @return Identificador
		 */
		
		public String getId() {
			return this.id;
		}
	
		/**
		 * Devuelve el Dron
		 * @return Dron
		 */
		
		public Dron getDron() {
			return this.dron;
		}
	
		/**
		 * Devuelve el array de posiciones
		 * @return Posiciones de la Mision
		 */
		
		public Posicion[] getPosiciones() {
			return this.posiciones;
		}
		
		/**
		 * Devuelve el array de tiempos
		 * @return Tiempos de la Mision
		 */
	
		public long[] getTiempos() {
			return this.tiempos;
		}
	
		/**
		 * Devuelve el número de posiciones del array
		 * @return
		 */
		
		public int getnPosiciones() {
			return this.nPosiciones;
		}
	
		/**
		 * Devuelve el indice de la posicion del dron en el array
		 * @return Posicion en el array
		 */
		
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
	
		/**
		 * Devuelve la informacion de la Mision
		 */
		
		@Override
		public String toString() {
			return "Mision [id=" + id + ", dron=" + dron + ", posiciones=" + Arrays.toString(posiciones) + ", tiempos="
					+ Arrays.toString(tiempos) + ", nPosiciones=" + nPosiciones + ", posicion=" + posicion + "]";
		}
	
		
		/**
		 * Añade una posicion y un tiempo al array correspondiente
		 * @param pos Nueva Posicion en el array
		 * @param t Nuevo tiempo en el array
		 */
		
		public void addPosT(Posicion pos, long t) {
			if (pos != null && this.nPosiciones < this.posiciones.length) {
				this.posiciones[this.nPosiciones] = pos;
				this.tiempos[this.nPosiciones] = t;
				this.nPosiciones++;
			}
		}
	
		/**
		 * Devuelve si la mision esta activa
		 * @return true, si esta activa, y false si no
		 */
		
		public boolean activa() {
			return this.posicion < this.nPosiciones && this.posiciones[this.posicion] != null;
		}
	
		/**
		 * Actualiza el estado de la mision, de forma que si se ha alcanzado una posicion se pasa a la siguiente. 
		 * @param t Nuevo tiempo del dron. Debe ser mayor que el inicial de la fase en la que esta.
		 */
		
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
	
	
