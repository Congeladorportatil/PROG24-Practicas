package es.upm.dit.prog.practica3;
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

public final class PruebaPractica3 {

	//private double delta = 0.1;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// testCentroControl1: ctor 
	@Test (timeout=500)
	public void testCentroControl1(){
		CentroControl cc = new CentroControl();
		String s = cc.toString();
		assertTrue(s != null);
	}	

	//testCentroControl2.addDron vacio
	@Test (timeout=500)
	public void testCentroControl2(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		Dron[] drones0 = new Dron[10];
		for (int i = 0; i < drones0.length; i++)
		  drones0[i] = new Dron("d" + i, pos, 0, vel);
		Dron[] drones1 = new Dron[] {drones0[0]};
		CentroControl cc = new CentroControl();
		cc.addDron(null);
		assertArrayEquals("CentroControl.addDron null", new Dron[] {}, cc.getDrones());
		cc.addDron(drones0[0]);
		assertArrayEquals("CentroControl.addDron con drones vacío", drones1, cc.getDrones());
	}	

	//testCentroControl3.addDron a medias
	@Test (timeout=500)
	public void testCentroControl3(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		Dron[] drones0 = new Dron[10];
		for (int i = 0; i < drones0.length; i++)
		  drones0[i] = new Dron("d" + i, pos, 0, vel);
		CentroControl cc = new CentroControl();
		cc.addDron(drones0[0]);
		Dron[] drones2 = new Dron[] {drones0[0], drones0[1], drones0[2]};
		cc.addDron(drones0[1]);
		cc.addDron(drones0[2]);
		assertArrayEquals("CentroControl.addDron con drones a medias", drones2, cc.getDrones());
	}	

	//testCentroControl4.addDron  lleno
	@Test (timeout=500)
	public void testCentroControl4(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		Dron d = new Dron("NO", pos, 0, vel);
		Dron[] drones0 = new Dron[10];
		for (int i = 0; i < drones0.length; i++)
		  drones0[i] = new Dron("d" + i, pos, 0, vel);
		CentroControl cc = new CentroControl();
		cc.addDron(d);
		assertArrayEquals("CentroControl.addDron con drones vacío", new Dron[] {d}, cc.getDrones());
		for (int i = 0; i < drones0.length; i++)
			  cc.addDron(drones0[i]);
		assertArrayEquals("CentroControl.addDron con más de 10 drones", drones0, cc.getDrones());
	}	

	//testCentroControl5.addMision vacío
	@Test (timeout=500)
	public void testCentroControl5(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		Dron d = new Dron("d0", pos, 0, vel);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 5);
		Mision[] misiones1 = new Mision[] {misiones0[0]};
		CentroControl cc = new CentroControl();
		cc.addMision(null);
		assertArrayEquals("CentroControl.addMision null", new Mision[] {}, cc.getMisiones());
		cc.addMision(misiones0[0]);
		assertArrayEquals("CentroControl.addMision con misiones vacío", misiones1, cc.getMisiones());
	}	

	//testCentroControl6.addMision a medias
	@Test (timeout=500)
	public void testCentroControl6(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		Dron d = new Dron("d0", pos, 0, vel);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 5);
		CentroControl cc = new CentroControl();
		cc.addMision(misiones0[0]);
		Mision[] misiones2 = new Mision[] {misiones0[0], misiones0[1], misiones0[2]};
		cc.addMision(misiones0[1]);
		cc.addMision(misiones0[2]);
		assertArrayEquals("CentroControl.addMision con misiones a medias", misiones2, cc.getMisiones());
	}	

	
	//testCentroControl7.addMision  lleno
	@Test (timeout=500)
	public void testCentroControl7(){
		Posicion pos = new Posicion(0,0,0);
		Posicion vel = new Posicion(0,0,0);
		Dron d = new Dron("d0", pos, 0, vel);
		Mision m = new Mision("mNO", d, 6);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 6);
		CentroControl cc = new CentroControl();
		cc.addMision(m);
		for (int i = 0; i < misiones0.length; i++)
			  cc.addMision(misiones0[i]);
		assertArrayEquals("CentroControl.addMision con más de 10 drones", misiones0, cc.getMisiones());
	}	

	//testCentroControl8.update 
	@Test (timeout=500)
	public void testCentroControl8(){
		int n = 10;
		Dron[] drones = new Dron[n];
		Mision[] misiones = new Mision[n];
		CentroControl cc = new CentroControl();
		for (int i = 0; i < n; i++) {
			Posicion p = new Posicion(10*i, 10*i, 10*i);
			drones[i] = new Dron("d" + i, p, 0, new Posicion(0,0,1));
			cc.addDron(drones[i]);	
			misiones[i] = new Mision ("m"+i, drones[i], 5);
			for (int j = 0; j < 5; j++)
				misiones[i].addPosT(new Posicion((10*i) +(10*j), (10*i)+(10*j), (10*i)+(10*j)), j*10);
			cc.addMision(misiones[i]);
		}
		for (int j = 0; j < 5; j++) {
			cc.update(j*10);
			for (int i =0 ; i < misiones.length; i++) {
				assertEquals("CentroControl update: drones avanzan mal ",
						new Posicion((10*i) +(10*j), (10*i)+(10*j), (10*i)+(10*j)),
						misiones[i].getDron().getPos());
				assertEquals("CentroControl update: la mision " + i +" avanza mal" +(j*10) , j+1, misiones[i].getPosicion());
				if (j < 4)
					assertTrue("CentroControl update: la mision " + i +" activa mal " +(j*10), misiones[i].activa());
				else
					assertFalse("CentroControl update: la mision " + i +" activa mal " +(j*10), misiones[i].activa());

			}
		}
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
			
			CentroControl.class.getClassLoader().loadClass(CentroControl.class.getCanonicalName());
			CentroControl.class.getConstructor();
			CentroControl.class.getMethod("toString");
			CentroControl.class.getMethod("addDron", Dron.class);
			CentroControl.class.getMethod("addMision", Mision.class);
			CentroControl.class.getMethod("getDrones");
			CentroControl.class.getMethod("getMisiones");
			CentroControl.class.getMethod("update", Long.TYPE);
			
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de CentroControl, Posicion, Dron o Mision");
			//return "Error al cargar las clases Vector o Dron o algunos de sus métodos. Repasar identificadores y firmas de métodos";
		}
		//return null;
	}
	
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(PruebaPractica3.class);
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
