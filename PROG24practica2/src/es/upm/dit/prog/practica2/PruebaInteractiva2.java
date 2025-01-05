package es.upm.dit.prog.practica2;

import java.util.Scanner;

import javax.swing.SwingUtilities;

//import java.util.Arrays;

public class PruebaInteractiva2 {
	
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
			"show \n muestra un diagrama con la situación actual de this.mision y this.dron1"

	};
	
	private Posicion vel;
	private Posicion pos;
	private Dron dron1;
	private Dron dron2;
	private Mision mision;
	
	public PruebaInteractiva2() {
		this.init();
	}

	public void init() {
		this.vel = new Posicion(0,0,0);
		this.pos = new Posicion(0,0,0);
		this.dron1 = new Dron("d1", this.pos, 0, this.vel);//, true);
		this.dron2 = new Dron("d2", this.pos, 0, this.vel);//, true);
		this.mision = new Mision("m0", this.dron1, 6);
	}
	
	public String run (String c) {
		c = c.toLowerCase();
		String[] fs = c.trim().split(" +");
		
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
					+ "this.dron2=" + this.dron2.toString() + "\n";
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
				Dron nuevo = new Dron (fs[1], this.pos, Long.parseLong (fs[2]), this.vel); //, Boolean.parseBoolean(fs[3]));
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
				this.mision.update(Long.parseLong(fs[1]));
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
	            	panel.init(dron1, mision, dron1.getT(), dron1.getT(), 1); 
	            	panel.createAndShowGUI();
	            }
	        });
			return "viewer launched";
		}

		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva2 m = new PruebaInteractiva2();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

