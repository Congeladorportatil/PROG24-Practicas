package es.upm.dit.prog.practica4;
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

public final class PruebaPractica4 {

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

	// SelectorMisionTrue
	@Test (timeout=500)
	public void testSelectores1(){
		Posicion p = new Posicion(0,0,0);
		Posicion v = new Posicion(0,0,0);
		Dron d = new Dron ("d1", p, 0, v);
		Mision m = new Mision("m1", d, 1);
		SelectorMision sm = new SelectorMisionTrue();
		assertTrue("SelectorMisionTrue", sm.seleccionar(m));
		assertFalse("SelectorMisionTrue", sm.seleccionar(null));
	}	

	// SelectorMisionActiva
	@Test (timeout=500)
	public void testSelectores2(){
		Posicion p = new Posicion(0,0,0);
		Posicion v = new Posicion(0,0,0);
		Dron d = new Dron ("d1", p, 0, v);
		Mision m = new Mision("m1", d, 2);
		m.addPosT(p, 0);
		m.addPosT(p, 10);
		CentroControl cc = new CentroControl();
		cc.addDron(d);
		cc.addMision(m);
		SelectorMision sm = new SelectorMisionActiva();
		assertFalse("SelectorMisionActiva", sm.seleccionar(null));
		assertTrue("SelectorMisionActiva", sm.seleccionar(m));
		cc.update(0);
		assertTrue("SelectorMisionActiva", sm.seleccionar(m));
		cc.update(10);
		assertFalse("SelectorMisionActiva", sm.seleccionar(m));
	}	

	// SelectorMisionDronAterrizando
	@Test (timeout=500)
	public void testSelectores3(){
		Posicion p = new Posicion(0,0,0);
		Posicion v = new Posicion(0,0,0);
		Dron d = new Dron ("d1", p, 0, v);
		Mision m = new Mision("m1", d, 1);
		SelectorMision sm = new SelectorMisionDronAterrizando();
		assertFalse("SelectorMisionDronAterrizando", sm.seleccionar(null));
		assertFalse("SelectorMisionDronAterrizando", sm.seleccionar(m));
		v = new Posicion(0,0, -10);
		d = new Dron("d1", p, 0, v);
		m = new Mision("m1", d, 1);
		assertTrue("SelectorMisionDronAterrizando", sm.seleccionar(m));
	}	

	// SelectorMisionDronDespegando
	@Test (timeout=500)
	public void testSelectores4(){
		Posicion p = new Posicion(0,0,0);
		Posicion v = new Posicion(0,0,0);
		Dron d = new Dron ("d1", p, 0, v);
		Mision m = new Mision("m1", d, 1);
		SelectorMision sm = new SelectorMisionDronDespegando();
		assertFalse("SelectorMisionDronDespegando", sm.seleccionar(null));
		assertFalse("SelectorMisionDronDespegando", sm.seleccionar(m));
		v = new Posicion(0,0, 10);
		d = new Dron("d1", p, 0, v);
		m = new Mision("m1", d, 1);
		assertTrue("SelectorMisionDronDespegando", sm.seleccionar(m));
	}	

	// SelectorMisionDronAlejado
	@Test (timeout=500)
	public void testSelectores5(){
		Posicion p = new Posicion(0,0,0);
		Posicion v = new Posicion(0,0,0);
		Dron d = new Dron ("d1", p, 0, v);
		Mision m = new Mision("m1", d, 1);
		SelectorMision sm = new SelectorMisionDronAlejado(new Posicion(10,0,0), 1);
		assertFalse("SelectorMisionDronAlejado", sm.seleccionar(null));
		assertTrue("SelectorMisionDronAlejado", sm.seleccionar(m));
		sm = new SelectorMisionDronAlejado(new Posicion(0,0,0), 1);
		assertFalse("SelectorMisionDronAlejado", sm.seleccionar(m));
	}	

	// SelectorMisionEnPeligro
	@Test (timeout=500)
	public void testSelectores6(){
		Posicion p1 = new Posicion(0,0,0);
		Posicion v1 = new Posicion(0,0,0);
		Dron d1 = new Dron ("d1", p1, 0, v1);
		Mision m1 = new Mision("m1", d1, 3);
		CentroControl cc = new CentroControl();
		cc.addDron(d1);
		cc.addMision(m1);
		
		Posicion p2 = new Posicion(0,0,0);
		Posicion v2 = new Posicion(0,0,0);
		Dron d2 = new Dron ("d2", p2, 0, v2);
		Mision m2 = new Mision("m2", d2, 3);
		cc.addDron(d2);
		cc.addMision(m2);
		
		Posicion p3 = new Posicion(10,10,0);
		Dron d3 = new Dron ("d1", p2, 0, v2);
		Mision m3 = new Mision("m3", d3, 3);
		cc.addDron(d3);
		cc.addMision(m3);
		
		Dron d4 = new Dron("d4", p3, 0, v2);
		Mision m4 = new Mision("m3", d4, 3);
		cc.addDron(d4);
		cc.addMision(m4);
		
		SelectorMision sm = new SelectorMisionDronEnPeligro(cc.getDrones());
		Mision[] misionesEnPeligro = new Mision[] {m1, m2, m3};
		assertArrayEquals("SelectorMisionDronEnPeligro", misionesEnPeligro, cc.getMisiones(sm));
	}	

	// getMisiones
	@Test (timeout=500)
	public void testSelectores7(){
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
		assertArrayEquals("CentroControl.addMision con misiones a medias", misiones2, cc.getMisiones(new SelectorMisionTrue()));

	}	

	
	
	@Test (timeout=500)
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
			CentroControl.class.getMethod("getMisiones", SelectorMision.class);
			CentroControl.class.getMethod("update", Long.TYPE);
			
			SelectorMision.class.getClassLoader().loadClass(SelectorMision.class.getCanonicalName());
			SelectorMisionTrue.class.getClassLoader().loadClass(SelectorMisionTrue.class.getCanonicalName());
			SelectorMisionTrue.class.getMethod("seleccionar",Mision.class);
			SelectorMisionActiva.class.getClassLoader().loadClass(SelectorMisionActiva.class.getCanonicalName());
			SelectorMisionActiva.class.getMethod("seleccionar",Mision.class);
			SelectorMisionDronAlejado.class.getClassLoader().loadClass(SelectorMisionDronAlejado.class.getCanonicalName());
			SelectorMisionDronAlejado.class.getConstructor(Posicion.class,Double.TYPE);
			SelectorMisionDronAlejado.class.getMethod("seleccionar",Mision.class);
			
			SelectorMisionDronAterrizando.class.getClassLoader().loadClass(SelectorMisionDronAterrizando.class.getCanonicalName());
			SelectorMisionDronAterrizando.class.getMethod("seleccionar",Mision.class);
			
			SelectorMisionDronDespegando.class.getClassLoader().loadClass(SelectorMisionDronDespegando.class.getCanonicalName());
			SelectorMisionDronDespegando.class.getMethod("seleccionar",Mision.class);
			SelectorMisionDronEnPeligro.class.getClassLoader().loadClass(SelectorMisionDronEnPeligro.class.getCanonicalName());
			SelectorMisionDronEnPeligro.class.getMethod("seleccionar",Mision.class);
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de Selectores, CentroControl, Posicion, Dron o Mision");
			//return "Error al cargar las clases Vector o Dron o algunos de sus métodos. Repasar identificadores y firmas de métodos";
		}
		//return null;
	}
	
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(PruebaPractica4.class);
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
