package es.upm.dit.prog.practica1;

import java.util.Objects;

public class Dron {
		private static final long SAFETY_DISTANCE = 2;
		private String id;
		private Posicion pos;
		private long t;
		private Posicion vel;
		
		public Dron(String id, Posicion pos, long t, Posicion vel) {
			this.id = id;
			this.pos = pos;
			this.t = t;
			this.vel = vel;
		}
		public String getId() {
	        return id;
	    }

	    public Posicion getPos() {
	        return pos;
	    }

	    public long getT() {
	        return t;
	    }

	    public Posicion getVel() {
	        return vel;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public void setPos(Posicion pos) {
	        this.pos = pos;
	    }

	    public void setT(long t) {
	        this.t = t;
	    }

	    public void setVel(Posicion vel) {
	        this.vel = vel;
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
			Dron other = (Dron) obj;
			return Objects.equals(id, other.id);
		}
		
		@Override
		public String toString() {
			return "Dron [id=" + id + ", pos=" + pos + ", t=" + t + ", vel=" + vel + "]";
		}
	    
		public void mover(long t) {
			if (t >= this.t) {
				double velX = vel.getX();
				double velY = vel.getY();
				double velZ = vel.getZ();

				double posX = pos.getX() + (velX * (t - this.t));
				double posY = pos.getY() + (velY * (t - this.t));
				double posZ = pos.getZ() + (velZ * (t - this.t));

				pos = new Posicion(posX, posY, posZ);
				this.t = t;
			} 
	    }
		
	    public boolean peligro(Dron otro) {
	        if (otro != null && !this.equals(otro)) {
	            double distanciaActual = this.pos.distancia(otro.getPos());
	            return distanciaActual < SAFETY_DISTANCE;
	        }
	        return false;
	    }
	    
	    
}
