package es.upm.dit.prog.practica1;

import java.util.Scanner;

//import javax.swing.SwingUtilities;

public class PruebaInteractiva1 {
	
	private String[] cmds = new String[] {
			"hello \n saludo",
			"help \n lista de órdenes",
			"exit \n salir",
			"status \n valores de variables",
			"clear \n reset de variables",
			"pos x y z \n crea this.pos que es una posicion con x:double y:double z:double",
			"vel x y z \n crea this.vel que es una velocidad con x:double y:double z:double",
			"dron id t \n crea this.dron1 nuevo con id:String this.pos t:long this.vel y guarda el anterior en this.dron2",
			"addvel \n añade vel a dron1",
			"mover1 t \n mueve this.dron1 con t:long",
			"mover2 t \n mueve this.dron2 con t:long",
			"peligro \n comprueba si el this.dron1 y this.dron2 están en peligro"
	};

	private Posicion vel;
	private Posicion pos;
	private Dron dron1;
	private Dron dron2;
	
	public PruebaInteractiva1() {
		this.init();
	}

	public void init() {
		this.vel = new Posicion(0,0,0);
		this.pos = new Posicion(0,0,0);
		this.dron1 = new Dron("d1", this.pos, 0, this.vel);//, true);
		this.dron2 = new Dron("d2", this.pos, 0, this.vel);//, true);
	}
	
	public String run (String c) {
		c = c.toLowerCase();
		String[] fs = c.trim().split(" +");
		int i = 0;
		// practica1
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
		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva1 m = new PruebaInteractiva1();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

