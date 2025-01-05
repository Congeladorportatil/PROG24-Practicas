package es.upm.dit.prog.practica1;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public final class PruebaPractica1 {

	private double delta = 0.1;
	
	@Before
	public void setUp() throws Exception {
 	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (timeout=500)
	public void testPosicion1(){
		// ctor, get, set
		try {
			//Posicion v0 = new Posicion (0.0, 0.0, 0.0);
			Posicion v1 = new Posicion (100.0, 100.0, 100.0);
			assertEquals("Posicion.getX", 100.0, v1.getX(), delta);
			assertEquals("Posicion.getY", 100.0, v1.getY(), delta);
			assertEquals("Posicion.getZ", 100.0, v1.getZ(), delta);
			v1.setX(-100.0);
			v1.setY(-100.0);
			v1.setZ(-100.0);
			assertEquals("Posicion.getX", -100.0, v1.getX(), delta);
			assertEquals("Posicion.getY", -100.0, v1.getY(), delta);
			assertEquals("Posicion.getZ", -100.0, v1.getZ(), delta);
		}
		catch (Exception e) {
			fail("<p>Fallo detectado en Posicion: constructor, accesores o modificadores. </p>");
		}
	}
	
	@Test (timeout=500)
	public void testPosicion2(){
		// modulo, angulo
		try {
			Posicion v0 = new Posicion (0.0, 0.0, 0.0);
			Posicion v1 = new Posicion (100.0, 100.0, 100.0);
			assertEquals("Posicion.modulo", 0.0, v0.modulo(), delta);
			assertEquals("Posicion.modulo", 100.0*Math.sqrt(3.0), v1.modulo(), delta);
		}
		catch (Exception e) {
			fail("<p>Fallo detectado en Posicion.modulo. </p>");
		}
	}

	@Test (timeout=500)
	public void testPosicion3(){
		// distancia
		try {
			Posicion v0 = new Posicion (0.0, 0.0, 0.0);
			Posicion v1 = new Posicion (100.0, 100.0, 100.0);
			assertEquals("Posicion.distancia", 100.0*Math.sqrt(3.0), v0.distancia(v1), delta);
			assertEquals("Posicion.distancia", 100.0*Math.sqrt(3.0), v1.distancia(v0), delta);
			assertEquals("Posicion.distancia", 0.0, v0.distancia(v0), delta);
			assertEquals("Posicion.distancia", 0.0, v1.distancia(v1), delta);
		}
		catch (Exception e) {
			fail("<p>Fallo detectado en Posicion.distancia. </p>");
		}
	}

	@Test (timeout=500)
	public void testDron1(){
		// ctor, get, set
		try {
			Posicion p = new Posicion (0.0, 0.0, 0.0);
			Posicion v = new Posicion (100.0, 100.0, 100.0);
			long t = 0;
			Dron d = new Dron ("d0", p, t, v);
			assertEquals("Dron.getPos", p, d.getPos());
			assertEquals("Dron.getV", v, d.getVel());
			assertEquals("Dron.getT", t, d.getT(), delta);
		}
		catch (Exception e) {
			fail("<p>Fallo en el constructor, accesores o modificadores de Dron. </p>");
		}
	}
	
	@Test (timeout=500)
	public void testDron2(){
		// equals
		try {
			
			Posicion p = new Posicion(0,0,0);
			Posicion v = new Posicion(0,0,0);
			int t = 0;
			Dron d1 = new Dron ("d1", p, t, v);
			Dron d2 = new Dron ("d1", p, t, v);
			assertTrue("Dron.equals", d1.equals(d2));
			assertTrue("Dron.equals", d2.equals(d1));
			assertTrue("Dron.equals", d2.equals(d2));
			d2.setId("d2");
			assertFalse("Dron.equals", d1.equals(d2));
			assertFalse("Dron.equals", d2.equals(d1));
			assertTrue("Dron.equals", d2.equals(d2));
		}
		catch (Exception e) {
			fail("<p>Fallo en Dron.equals). </p>");
		}
	}

	@Test (timeout=500)
	public void testDron3(){
		// mover sin cambio de t
		try {
			// mover en el mismo tiempo
			Posicion p = new Posicion(0,0,0);
			Posicion v = new Posicion(10,10,10);
			int t = 0;
			Dron d = new Dron ("d1", p, t, v);
			d.mover(t);
			assertEquals("Dron.mover con el mismo t", p, d.getPos());
			assertEquals("Dron.mover con el mismo t", t, d.getT());

		}
		catch (Exception e) {
			fail("<p>Fallo en Dron.mover cuando no cambia t. </p>");
		}
	}
	
	@Test (timeout=500)
	public void testDron4(){
		// mover t diferente v 0
		try {
			Posicion p = new Posicion(0,0,0);
			Posicion v = new Posicion(0,0,0);
			int t = 0;
			Dron d = new Dron ("d1", p, t, v);
			
			// mover tiempo distinto, v 0
			t+= 10;
			d.mover(t);
			assertEquals("Dron.mover con diferente t", p, d.getPos());
			assertEquals("Dron.mover con diferente t", t, d.getT());
		}
		catch (Exception e) {
			fail("<p>Fallo en Dron.mover cambia t pero v 0,0,0. </p>");
		}
	}

	@Test (timeout=500)
	public void testDron5(){
		// mover t diferente v <> 0
		try {
			Posicion p = new Posicion(0,0,0);
			Posicion v = new Posicion(0,0,0);
			int t = 0;
			Dron d = new Dron ("d1", p, t, v);
			t+= 10;
			d.mover(t);
			
			// mover tiempo distinto v <> 0
			v = new Posicion (-10, -10, 10);
			p = new Posicion(d.getPos().getX() + (v.getX() * 10),
					       d.getPos().getY() + (v.getY() * 10), 
					       d.getPos().getZ() + (v.getZ() * 10));
			t+= 10;
			d.setVel(v);
			d.mover(t);
			assertEquals("Dron.mover con diferente t y v <> 0", p, d.getPos());
			assertEquals("Dron.mover con diferente t y v <> 0", t, d.getT());
		}
		catch (Exception e) {
			fail("<p>Fallo en Dron.mover cambia t y v distinta de 0. </p>");
		}
	}
	
	// en peligro
	@Test (timeout=500)
	public void testDron6(){
		Posicion p1 = new Posicion(0,0,0);
		Posicion v1 = new Posicion(0,0,0);
		Dron d1 = new Dron ("d1", p1, 0, v1);
		Posicion p2 = new Posicion(0,0,0);
		Posicion v2 = new Posicion(0,0,0);
		assertFalse("Dron.peligro", d1.peligro(null));
		Dron d2 = new Dron ("d2", p2, 0, v2);
		assertTrue("Dron.peligro", d1.peligro(d2));
		Dron d3 = new Dron ("d1", p2, 0, v2);
		assertFalse("Dron.peligro", d1.peligro(d3));
		Posicion p3 = new Posicion(10,10,0);
		Dron d4 = new Dron("d4", p3, 0, v2);
		assertFalse("Dron.peligro", d1.peligro(d4));
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

		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de Posicion o Dron");
		}
	}
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(PruebaPractica1.class);
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
