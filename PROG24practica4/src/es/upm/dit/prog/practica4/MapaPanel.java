package es.upm.dit.prog.practica4;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
import java.io.IOException;
//import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class MapaPanel extends JPanel {
	private JFrame f;
	private static final long serialVersionUID = 1L;
	private Image im1;
	private Image im2;

	private CentroControl cc;

	private static final int WIDTH =  1300 / 2;
	private static final int HEIGHTZ = WIDTH / 3;
	private static final int SEP = 0;
	private static final int HEIGHTXY = WIDTH;
	private static final int HEIGHT = HEIGHTZ + SEP + HEIGHTXY;
	
	private int x0 = WIDTH / 2;
	private int y0 = HEIGHTZ + SEP + (HEIGHTXY / 2);
	private int z0 = HEIGHTZ;
    
	private static final int SIMULATION_SPEED = 500;//1000;    
	private Timer timer;
    private long t;
    
    private long tini;
    private long tfin;
    private long step;
	
	public MapaPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		try {
        	this.im1 = ImageIO.read(getClass().getResource("paracuellos.jpg"));
        			//new File("paracuellos.jpg"));
        	this.im2 = ImageIO.read(getClass().getResource("cielo.jpg"));
    	} catch (IOException e) {
        	e.printStackTrace();
    	}
	}

	public void init(CentroControl cc, long tini, long tfin, long step) {
		this.cc = cc;
		this.tini = tini;
		this.tfin = tfin;
		this.step = step;
		this.t = tini;
	}
	
    public void createAndShowGUI() {
        System.out.println("Launching PruebaCentroControl " + 
                SwingUtilities.isEventDispatchThread());
        System.out.println ("tini= " +this.tini + " tfin= "+ this.tfin + " step= " + this.step + " t= " + this.t);
        this.f = new JFrame("CentroControl ");
        //this.f.setResizable(false);
        //this.setSize(WIDTH+600, HEIGHT+300);
        //this.f.setSize(WIDTH+300, HEIGHT+300);
        this.f.setLayout(new BorderLayout());
        this.f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
     	this.f.add(this, BorderLayout.CENTER);

        if (this.tini < this.tfin) {
        	this.timer = new Timer(SIMULATION_SPEED, new ActionListener () {
        		public void actionPerformed(ActionEvent ae) {
        			step();
        		}
        	});
       		this.timer.start();
        }
    	JPanel controles = new JPanel();
    	controles.setLayout(new GridLayout(0,1));
    	this.f.add(controles,BorderLayout.SOUTH); //PAGE_END);
    	JPanel controlP1 = new JPanel();
    	controles.add(controlP1, "North");
    	JButton start = new JButton(">>");
    	controlP1.add(start);
    	start.addActionListener(new ActionListener () {
    		public void actionPerformed(ActionEvent ae) {
    			timer.start();  
    	    }
    	});
    	JButton stop = new JButton("||");
    	controlP1.add(stop);
    	stop.addActionListener(new ActionListener () {
    		public void actionPerformed(ActionEvent ae) {
    			timer.stop();  
    	    }
    	});
    	JButton step = new JButton(">");
    	controlP1.add(step);
    	step.addActionListener(new ActionListener () {
    		public void actionPerformed(ActionEvent ae) {
    			timer.stop(); 
    			step();
    	    }
    	});
        this.f.pack();
        this.f.setVisible(true);
    }
    
    public void step() {
    	if (this.t > this.tfin) {
    		this.timer.stop();
    		return;
    	}
 		f.setTitle ("Sim, t = " + this.t);
 		this.cc.update(this.t);
 		this.f.repaint();    	
    	this.t += this.step;
    }	

    public Dimension getPreferredSize() {
		return new Dimension(WIDTH,HEIGHT);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);  
		Color c = g.getColor();
		if (this.im2 == null) {
			g.setColor(Color.CYAN);
			g.fillRect(0,0, WIDTH, HEIGHTZ);
		} else {
			g.drawImage(this.im2, 0, 0, null);
		}
		g.setColor(Color.BLACK);
		this.lineagruesa(g, 0, z0, 2*x0, z0, 2.0f); 
		this.linea(g, x0, z0, x0, 0);
		for (int i = 0; i <= z0; i+=100) {
	   		linea(g, 0, z0 - i, 2*x0, z0 - i); 
			minitexto(g, ""+i, x0, z0 - i);
		}
		if (this.im1 == null) {
			g.fillRect(0,z0+SEP, WIDTH, HEIGHTXY);
		} else
			g.drawImage(this.im1, 0, z0+ SEP, null);
		g.setColor(Color.white);
		this.linea(g, 0, y0, 2*x0, y0);
		this.linea(g, x0, z0, x0, 2*y0);
		for (int i = 100; i <= 300; i+=100) {
			this.circulo(g, x0, y0, i);
			this.minitexto(g, ""+i, x0+i, y0);
		}
		for (Mision m: this.cc.getMisiones(null)) {
			if (m != null)
				this.paintMision(g, m);
		}
		for (Dron d: this.cc.getDrones()) {
			if (d != null) {
				this.paintDron (g, d, false);
			}
		}
		g.setColor(c);
	}
	
	public void paintMision(Graphics g, Mision m) {
		for (int i = 0; i < m.getPosiciones().length; i++) {
			if (m.getPosiciones()[i] != null) {
				this.circulo(g, x0 + m.getPosiciones()[i].getX(), y0 - m.getPosiciones()[i].getY(), 4);
   				this.circulo(g, x0 + m.getPosiciones()[i].getX(), z0 - m.getPosiciones()[i].getZ(), 4);

				this.texto(g, "" + i //m.getTiempos()[i]
						, x0 + m.getPosiciones()[i].getX()-25+(5*i), y0 - m.getPosiciones()[i].getY()+2);
   				this.texto(g, "" + i //m.getTiempos()[i]
   						, x0 + m.getPosiciones()[i].getX()-25+(5*i), z0 - m.getPosiciones()[i].getZ()+2);

				if (i < m.getPosiciones().length -1) {
					this.lineagruesa(g, x0 + m.getPosiciones()[i].getX(), y0 - m.getPosiciones()[i].getY(), x0 + m.getPosiciones()[i+1].getX(), y0 - m.getPosiciones()[i+1].getY(), 2.0f);
   					this.lineagruesa(g, x0 + m.getPosiciones()[i].getX(), z0 - m.getPosiciones()[i].getZ(), x0 + m.getPosiciones()[i+1].getX(), z0 - m.getPosiciones()[i+1].getZ(), 2.0f);
				}
			}
		}
	}   		
	
    public void paintDron(Graphics g, Dron d, boolean enPeligro) {
    	Color c = g.getColor();
    	g.setColor(new Color(10000 * (int) d.getPos().getZ()));
  		Stroke s0 = ((Graphics2D)g).getStroke();
   	   	((Graphics2D)g).setStroke(new BasicStroke(5));

    	this.triangulo(g, x0+ d.getPos().getX(), y0-d.getPos().getY(),
    			Math.atan2(d.getVel().getY(), d.getVel().getX()), 10,
    			new BasicStroke(5));
    	this.triangulo(g, x0+ d.getPos().getX(), y0-d.getPos().getY(),
    			Math.atan2(d.getVel().getY(), d.getVel().getX()) + Math.PI, 10,
    			new BasicStroke(5));
    	this.texto(g, d.getId() //+ ",z="+(int)(d.getPos().getZ())
    			, x0+ d.getPos().getX(), y0-d.getPos().getY());
   	   	g.drawRect((int)(x0+ d.getPos().getX() - (16/2)), (int)(z0-d.getPos().getZ() - (4/2)), 16, 4);
   	   	this.texto(g, d.getId(), x0+ d.getPos().getX(), z0-d.getPos().getZ());
   	   	((Graphics2D)g).setStroke(s0);  	  
    	((Graphics2D)g).setColor(c);
    }	
    
    public void triangulo(Graphics g, double xc, double yc, double angle, double r, Stroke s) {
     	int[] xs = new int [] {(int) (xc+(r*Math.cos(angle))),
    			(int) (xc+(r*Math.cos(angle + (2*Math.PI / 3)))),
    			(int) (xc+(r*Math.cos(angle + (2*2*Math.PI / 3))))
    	};
    	int[] ys = new int [] {(int) (yc+(r*Math.sin(angle))),
    			(int) (yc + (r*Math.sin(angle+(2*Math.PI/3)))),
    			(int) (yc + (r*Math.sin(angle+(2*2*Math.PI/3))))
    	};
   		g.drawPolygon (xs, ys, 3);
    	//g.fillPolygon (xs, ys, 3);		  
    }
    
    // basic rendering methods
    public void limpia(Graphics g, double x1, double y1, double x2, double y2) {
        g.clearRect((int)Math.round(x1),
    		(int)Math.round(y1),
    		(int)Math.round(x2),
    		(int)Math.round(y2));
    }
    
    public void texto(Graphics g, String str, double x, double y) {
    	g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(str, (int)Math.round(x+8), (int)Math.round(y-8));
    }

    public void minitexto(Graphics g, String str, double x, double y) {
    	g.setFont(new Font("Arial", Font.PLAIN, 8));
        g.drawString(str, (int)Math.round(x+2), (int)Math.round(y-2));
    }

    public void linea(Graphics g, double x1, double y1, double x2, double y2) {
        g.drawLine((int)Math.round(x1),
    	       (int)Math.round(y1),
    	       (int)Math.round(x2),
    	       (int)Math.round(y2));
    }
    
    public void lineagruesa(Graphics g, double x1, double y1, double x2, double y2, float thick) {
    	Stroke s0 = ((Graphics2D)g).getStroke();
    	((Graphics2D)g).setStroke(new BasicStroke(thick));
    	this.linea(g, x1, y1, x2, y2);
    	((Graphics2D)g).setStroke(s0);
    }

    public void circulo(Graphics g, double x, double y, double radio) {
	   	g.drawOval((int)Math.round(x-radio),
	       (int)Math.round(y-radio),
	       (int)Math.round(2*radio),
	       (int)Math.round(2*radio));
    }
       
    public void circuloRelleno(Graphics g, double x, double y, double radio) {
	   	g.fillOval((int)Math.round(x-radio),
	 	       (int)Math.round(y-radio),
	 	       (int)Math.round(2*radio),
	 	       (int)Math.round(2*radio));
	}
    
    public void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
    	this.drawArrowLine(g, x1, y1, x2, y2, 20, 6);
    }
    
    /**
     * Draw an arrow line between two points.
     * @param g the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow. Default=20
     * @param h  the height of the arrow. Default=6
     */
    public void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        //x1 = this.x0+x1;
        //x2 = this.x0+x2;
        //y1 = this.y0-y1;
        //y2 = this.y0-y2;
  	    int dx = x2 - x1;
        int dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d;
        double xn = xm;
        double ym = h;
        double yn = -h;
        double sin = dy / D;
        double cos = dx / D;
        double x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;
        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;
        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}
