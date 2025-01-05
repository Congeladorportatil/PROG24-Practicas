package es.upm.dit.prog.practica3;

import java.util.Scanner;

import javax.swing.SwingUtilities;

//import java.util.Arrays;

public class PruebaInteractiva3 {
	
	private String[] cmds = new String[] {
			"hello \n saludo",
			"help \n lista de órdenes",
			"exit \n salir",
			"status \n valores de variables",
			"clear \n reset de variables",
			"pos x y z \n crea this.pos que es un vector posicion con x:double y:double z:double",
			"vel x y z \n crea this.vel que es un vector velocidad con x:double y:double z:double",
			"dron id t \n crea this.dron1 nuevo con id:String this.pos t:long this.vel y guarda el anterior en this.dron2",
			"addvel \n añade vel a dron1",
			"mover1 t \n mueve this.dron1 con t:long",
			"mover2 t \n mueve this.dron2 con t:long",
			"peligro \n comprueba si el this.dron1 y this.dron2 están en peligro",
			//practica2
			"mision id n \n crea una misión con id:String this.dron1 n posiciones",
			"addpos t \n añade this.pos y t:long a this.mision",
			"update t \n actualiza this.mision con t:long",
			"show \n muestra un diagrama con la situación actual de this.mision y this.dron1",
			
			// practica3
			"adddron \n añade this.dron1 a this.cc",
			"getdrones \n obtiene drones no null de this.cc",
			"addmision \n añade this.mision1 a this.cc",
			"getmisiones \n obtiene misiones no null de this.cc",
			"updatecc t \n actualiza todos los elementos de this.cc con t:long",
			"sim tini tfin \n muestra una simulación del movimiento de los satelites de la estacion con: tini:long tfin:long"
	};
	
	private Posicion vel;
	private Posicion pos;
	private Dron dron1;
	private Dron dron2;
	private Mision mision;
	
	private CentroControl cc;
	
	
	public PruebaInteractiva3() {
		this.init();
	}

	public void init() {
		this.vel = new Posicion(0,0,0);
		this.pos = new Posicion(0,0,0);
		this.dron1 = new Dron("d1", this.pos, 0, this.vel);
		this.dron2 = new Dron("d2", this.pos, 0, this.vel);
		this.mision = new Mision("m0", this.dron1, 5);
		this.cc = new CentroControl();
	}
	
	public String run (String c) {
		c = c.toLowerCase();
		String[] fs = c.trim().split(" ");
		
		// practica1
		int i = 0;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) //"hello"
			return c;
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { //"help"
			String r = "";
			for (String ci: cmds)
				r += ci + "\n";
			return r;
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { //"exit"
			System.out.println("bye");
			System.exit(0);
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { //"status"
			return "this.vel=" + this.vel.toString() + "\n"
					+ "this.pos=" + this.pos.toString() + "\n"
					+ "this.dron1=" + this.dron1.toString() + "\n"
					+ "this.dron2=" + this.dron2.toString() + "\n"
					+ "this.cc=" + this.cc.toString() + "\n";
		}
		i++;		
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "clear"
			this.init();
			return "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "pos"
			try {
				this.pos = new Posicion(Double.parseDouble(fs[1]), Double.parseDouble(fs[2]), Double.parseDouble(fs[3]));
				return this.pos + "\n";				
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "vel"
			try {
				this.vel = new Posicion(Double.parseDouble(fs[1]), Double.parseDouble(fs[2]), Double.parseDouble(fs[3]));
				return this.vel + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "dron"
			try {
				Dron nuevo = new Dron (fs[1], this.pos, Long.parseLong (fs[2]), this.vel);
				this.dron2 = this.dron1;
				this.dron1 = nuevo;				
				return this.dron1.toString() +"\n"; 
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addVel"
			this.dron1.setVel(this.vel);
			return this.dron1.toString() + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "mover1"
			try {
				this.dron1.mover(Long.parseLong(fs[1]));
				return this.dron1.toString()+ "\n"; 
			} catch (Exception e) {
				return cmds[i]+ "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "mover2"
			try {
				this.dron2.mover(Long.parseLong(fs[1]));
				return this.dron2.toString()+ "\n"; 
			} catch (Exception e) {
				return cmds[i]+ "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "peligro"
			return "peligro= " + this.dron1.peligro(this.dron2); 
		}

		//practica2
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "mision"
			try {
				this.mision = new Mision (fs[1], this.dron1, Integer.parseInt(fs[2]));
				return this.mision.toString() + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addpos"
			try {
				this.mision.addPosT(this.pos, Long.parseLong(fs[1]));
				return this.mision.toString() + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "update t"
			try {
				this.mision.update(Integer.parseInt(fs[1]));
				return this.mision.toString() + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "show"
			System.out.println("Launching...");
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MapaPanel panel = new MapaPanel();
	            	panel.init(cc, 0, 0, 0); 
	            	panel.createAndShowGUI();
	            }
	        });
			return "viewer launched";
		}

		// practica3
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "adddron"
			this.cc.addDron(this.dron1);
			return this.cc.toString() + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "getdrones"
			String r = "";
			for (Dron d: this.cc.getDrones())
				r += d.toString();
			return r + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addmision"
			this.cc.addMision(this.mision);
			return this.cc.toString() + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "getmisiones"
			String r = "";
			for (Mision m: this.cc.getMisiones())
				r += m.toString();
			return r + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "updatecc"
			try {
				this.cc.update(Long.parseLong(fs[1]));
				return this.cc.toString()+ "\n"; 
			} catch (Exception e) {
				return cmds[i]+ "\n";
			}
		}
		
		
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "sim"
			try {
				long tini = Long.parseLong (fs[1]);
				long tfin = Long.parseLong (fs[2]);
			System.out.println("Launching sim from " + tini + " to " + tfin + " stepping at " + 1);
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MapaPanel panel = new MapaPanel();
	                panel.init (cc, tini, tfin, 1); 
	                panel.createAndShowGUI();
	            }
	        });
			return "viewer launched";
			} catch(Exception e) {
				return cmds[i];
			}
		}
		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva3 m = new PruebaInteractiva3();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

