package es.upm.dit.prog.practica2;
import static org.junit.Assert.*;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public final class PruebaPractica2 {

	//private double delta = 0.1;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// testMision1: ctor y accesores
	@Test (timeout=500)
	public void testMision1(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 1);
		Posicion[] poss = new Posicion[] {null};
		assertEquals("Mision.ctor", "m1", m.getId());
		assertEquals("Mision.ctor", d1, m.getDron());
		assertEquals("Mision.ctor", 0, m.getPosicion());
		assertEquals("Mision.ctor", 0, m.getnPosiciones());
		assertEquals("Mision.ctor", 1, m.getPosiciones().length);
		assertEquals("Mision.ctor", 1, m.getTiempos().length);
		assertArrayEquals("Mision.ctor", poss, m.getPosiciones());
	}

	// testMision2: addPosT vacía, llena
	@Test (timeout=500)
	public void testMision2(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 1);
		Posicion[] poss = new Posicion[] {pos};
		long[] ts = new long[] {t};
		m.addPosT(pos, t);
		assertArrayEquals("Mision.addPosT vacia", poss, m.getPosiciones());
		assertArrayEquals("Mision.addPosT vacia", ts, m.getTiempos());
		m.addPosT(pos, t);
		assertArrayEquals("Mision.addPosT llena", poss, m.getPosiciones());
		assertArrayEquals("Mision.addPosT llena", ts, m.getTiempos());
	}
	
	// testMision3: addPosT media
	@Test (timeout=500)
	public void testMision3(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 2);

		Posicion[] poss = new Posicion[] {new Posicion (0,0,0), new Posicion(1,1,1), new Posicion(2,2,2)};
		long[] ts = new long[] {0, 10, 20};
		
		m.addPosT(poss[0], ts[0]);
		assertArrayEquals("Mision.addPosT vacia", new Posicion[] {poss[0], null}, m.getPosiciones());
		assertArrayEquals("Mision.addPosT vacia", new long[] {ts[0], 0}, m.getTiempos());
		assertEquals("Mision.addPosT vacia", 1, m.getnPosiciones());
		
		m.addPosT(poss[1], ts[1]);
		assertArrayEquals("Mision.addPosT a medias", new Posicion[] {poss[0], poss[1]}, m.getPosiciones());
		assertArrayEquals("Mision.addPosT a medias", new long[] {ts[0], ts[1]}, m.getTiempos());
		assertEquals("Mision.addPosT vacia", 2, m.getnPosiciones());
		
		m.addPosT(poss[2], ts[2]);
		assertArrayEquals("Mision.addPosT completa", new Posicion[] {poss[0], poss[1]}, m.getPosiciones());
		assertArrayEquals("Mision.addPosT completa", new long[] {ts[0], ts[1]}, m.getTiempos());
		assertEquals("Mision.addPosT vacia", 2, m.getnPosiciones());

	}
		
	// testMision4: update camino feliz
	@Test (timeout=500)
	public void testMision4(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 3);

		Posicion[] poss = new Posicion[] {new Posicion (0,0,0), new Posicion(1,1,1), new Posicion(2,2,2)};
		long[] ts = new long[] {t, t+10, t+20};
		m.addPosT(poss[0], ts[0]);
		m.addPosT(poss[1], ts[1]);
		m.addPosT(poss[2], ts[2]);

		assertEquals("Mision.update antes de empezar", pos, m.getDron().getPos());
		assertEquals("Mision.update antes de empezar", 0, m.getPosicion());

		d1.mover(ts[0]);
		m.update(ts[0]);
		assertEquals("Mision.update 1", poss[0], m.getDron().getPos());
		assertEquals("Mision.update 1", 1, m.getPosicion());

		d1.mover(ts[1]);
		m.update(ts[1]);
		assertEquals("Mision.update 2", poss[1], m.getDron().getPos());
		assertEquals("Mision.update 2", 2, m.getPosicion());

		d1.mover(ts[2]);
		m.update(ts[2]);
		assertEquals("Mision.update 3", poss[2], m.getDron().getPos());
		assertEquals("Mision.update 3", 3, m.getPosicion());
		
		d1.mover(ts[2]);
		m.update(ts[2]);
		assertEquals("Mision.update inactiva", poss[2], m.getDron().getPos());
		assertEquals("Mision.update inactiva", 3, m.getPosicion());
	}

	// testMision5: update camino feliz, activa
	@Test (timeout=500)
	public void testMision5(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 3);

		Posicion[] poss = new Posicion[] {new Posicion (0,0,0), new Posicion(1,1,1), new Posicion(2,2,2)};
		long[] ts = new long[] {t, t+10, t+20};
		
		assertFalse("Mision.activa antes de empezar", m.activa()); 
		
		m.addPosT(poss[0], ts[0]);
		m.addPosT(poss[1], ts[1]);
		m.addPosT(poss[2], ts[2]);

		assertTrue("Mision.activa 1", m.activa());

		d1.mover(ts[0]);
		m.update(ts[0]);
		assertTrue("Mision.activa 2", m.activa());

		d1.mover(ts[1]);
		m.update(ts[1]);
		assertTrue("Mision.activa 3", m.activa());

		d1.mover(ts[2]);
		m.update(ts[2]);
		assertFalse("Mision.activa inactiva", m.activa());
		
		d1.mover(ts[2]);
		m.update(ts[2]);
		assertFalse("Mision.activa inactiva", m.activa());
	}
	
	// testMision6: update camino feliz, velocidades
	@Test (timeout=500)
	public void testMision6(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 3);

		Posicion[] poss = new Posicion[] {new Posicion (0,0,0), new Posicion(1,1,1), new Posicion(2,2,2)};
		long[] ts = new long[] {t, t+10, t+20};
		
		m.addPosT(poss[0], ts[0]);
		m.addPosT(poss[1], ts[1]);
		m.addPosT(poss[2], ts[2]);

		assertEquals("Mision.activa antes de empezar", vel, d1.getVel());
		
		d1.mover(ts[0]);
		m.update(ts[0]);
		assertEquals("Mision.activa 1", new Posicion(0.1, 0.1, 0.1), d1.getVel());

		d1.mover(ts[1]);
		m.update(ts[1]);
		assertEquals("Mision.activa 2", new Posicion(0.1, 0.1, 0.1), d1.getVel());

		d1.mover(ts[2]);
		m.update(ts[2]);
		assertEquals("Mision.activa 3", vel, d1.getVel());
		
		d1.mover(ts[2]);
		m.update(ts[2]);
		assertEquals("Mision.activa inactiva", vel, d1.getVel());
	}	
	
	// testMision7: update fallo de tiempos
	@Test (timeout=500)
	public void testMision7(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		long t = 0;
		Dron d1 = new Dron("d1", pos, t, vel);
		Mision m = new Mision ("m1", d1, 3);

		Posicion[] poss = new Posicion[] {new Posicion (0,0,0), new Posicion(1,1,1), new Posicion(2,2,2)};
		long[] ts = new long[] {t, t+10, t+20};
		
		m.addPosT(poss[0], ts[0]);
		m.addPosT(poss[1], ts[1]);
		m.addPosT(poss[2], ts[2]);

		assertEquals("Mision.activa antes de empezar", vel, d1.getVel());
		
		d1.mover(ts[0]);
		m.update(ts[0]);
		assertEquals("Mision.activa 1", new Posicion(0.1, 0.1, 0.1), d1.getVel());

		d1.mover(ts[1]);
		m.update(ts[1]-2);
		assertEquals("Mision.activa con tiempos diferentes", new Posicion(0.1, 0.1, 0.1), d1.getVel());
		assertEquals("Mision.update con tiempos diferentes2", 1, m.getPosicion());

		m.update(ts[1]);
		assertEquals("Mision.activa 2", new Posicion(0.1, 0.1, 0.1), d1.getVel());
		assertEquals("Mision.update 2", 2, m.getPosicion());
		
		d1.mover(ts[2]);
		m.update(ts[2]);
		assertEquals("Mision.activa 3", vel, d1.getVel());
		
		d1.mover(ts[2]);
		m.update(ts[2]);
		assertEquals("Mision.activa inactiva", vel, d1.getVel());
	}	

	
	@Test (timeout=500)
	//public static String 
	public void checkDependencies() {
		try {
			Posicion.class.getClassLoader().loadClass(Posicion.class.getCanonicalName());
			Posicion.class.getConstructor(Double.TYPE,Double.TYPE,Double.TYPE);
			Posicion.class.getMethod("getX");
			Posicion.class.getMethod("getY");
			Posicion.class.getMethod("getZ");
			Posicion.class.getMethod("setX", Double.TYPE);
			Posicion.class.getMethod("setY", Double.TYPE);
			Posicion.class.getMethod("setZ", Double.TYPE);
			Posicion.class.getMethod("equals", Object.class);
			Posicion.class.getMethod("toString");
			Posicion.class.getMethod("modulo");
			Posicion.class.getMethod("distancia", Posicion.class);

			Dron.class.getClassLoader().loadClass(Dron.class.getCanonicalName());
			Dron.class.getConstructor(String.class,Posicion.class,Long.TYPE,Posicion.class);
			Dron.class.getMethod("getId");
			Dron.class.getMethod("getPos");
			Dron.class.getMethod("getT");
			Dron.class.getMethod("getVel");
			Dron.class.getMethod("setId", String.class);
			Dron.class.getMethod("setPos",Posicion.class);
			Dron.class.getMethod("setT", Long.TYPE);
			Dron.class.getMethod("setVel",Posicion.class);
			Dron.class.getMethod("equals", Object.class);
			Dron.class.getMethod("toString");
			Dron.class.getMethod("mover", Long.TYPE);
			Dron.class.getMethod("peligro", Dron.class);
			
			Mision.class.getClassLoader().loadClass(Mision.class.getCanonicalName());
			Mision.class.getConstructor(String.class, Dron.class, Integer.TYPE);
			Mision.class.getMethod("getId");
			Mision.class.getMethod("getDron");
			Mision.class.getMethod("getPosiciones");
			Mision.class.getMethod("getTiempos");
			Mision.class.getMethod("getnPosiciones"); // CUIDADO con la n
			Mision.class.getMethod("getPosicion");
			Mision.class.getMethod("equals", Object.class);
			Mision.class.getMethod("toString");
			Mision.class.getMethod("addPosT", Posicion.class, Long.TYPE);
			Mision.class.getMethod("activa");
			Mision.class.getMethod("update", Long.TYPE);
			
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de Posicion, Dron o Mision");
			//return "Error al cargar las clases Vector o Dron o algunos de sus métodos. Repasar identificadores y firmas de métodos";
		}
		//return null;
	}
	
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(PruebaPractica2.class);
			System.out.println("Se ejecutan " + request.getRunner().testCount() + " pruebas");
			juc.addListener(new RunListener() {
				@Override
			    public void testStarted(Description description) throws Exception {
					System.out.print("*");
			    }
			});
			result = juc.run(request);
		} catch (Throwable t) {
			System.out.println("Error al cargar el evaluador. La evaluación no se puede hacer con errores de compilación, o si identificadores y signaturas de la práctcia no se ajustan al enunciado.");
			System.exit(-1);
		} 
	    if(result == null || result.getFailureCount()==result.getRunCount() && result.getFailures().get(0).getMessage()==null){
	        fails = "No se han podido realizar pruebas a su entrega: Compruebe su clase y el paquete tienen nombres correctos y que los constructores de su clase funcionan de forma correcta";
	    }
	    else {
	    	  for (Failure failure : result.getFailures()) {
	              String cadena = failure.getMessage();
	              if(cadena!=null && cadena.contains("timed out")){
	                      String metodo = failure.getTestHeader().split("_")[0];
	                      cadena = "<p>Error detectado al probar el metodo "+metodo+": Ha excedido el tiempo de prueba. Compruebe que no tiene bucles infinitos en su codigo.</p> ";
	              }
	              fails += "\n"+cadena+".";
	    	  }
		}
	    return fails;
    }
        
    public static final void main(String[] args) {
    	System.out.println("Ejecutando las pruebas");
	    String fails=runJUnit();
	    if (fails.length() > 0) {
			System.out.println("Se han encontrado fallos en la ejecución:");
			System.out.println(fails);
		}
	    String grade=""+10.0*(result.getRunCount()-result.getFailureCount())/result.getRunCount();
	    System.out.println();
		System.out.println("La nota calculada ha sido: "+grade);
		System.out.println("Esta calificación es simplemente una orientación.");
		System.out.println("La evaluación definitiva se realizará cuando acabe el plazo de entrega de esta práctica.");
    }
}
