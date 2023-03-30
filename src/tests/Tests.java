package tests;

import java.lang.reflect.*;
import java.util.ArrayList;

import org.junit.*;
import org.junit.runners.MethodSorters;

import engine.Game;
import model.world.Collapse;
import model.world.Disaster;
import model.world.Fire;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
	
	@Test
	public void A_testDisasterID() {
		Class<Disaster> myClass = Disaster.class;
		assertTrue("Disaster should be an abstract class", Modifier.isAbstract(myClass.getModifiers()));
	}
	
	@Test
	public void A_testDisasterName() {
		Fire fire = new Fire();
		assertEquals("Fire name is initialized incorrectly", "Fire 1", fire.getName());
		Collapse collapse = new Collapse();
		assertEquals("Collapse name is initialized incorrectly", "Collapse 2", collapse.getName());
		assertEquals("timer is initialized", 2, fire.getTimer());
		assertEquals("timer is initialized", 2, collapse.getTimer());
	}
	
	@Test
	public void A_testPrivateInstanceVariables_Disasters() {
		Field[] fields = Disaster.class.getDeclaredFields();
		for (Field field : fields) {
			if (!java.lang.reflect.Modifier.isPrivate(field.getModifiers())) {
				fail("Instance variable " + field.getName() + " is not private");
			}
		}
	}
	
	 @Test
	    public void A_testSetterMethod() {
	        try {
	            Method setIDMethod = Disaster.class.getDeclaredMethod("setID", int.class);
	            assertFalse("ID is READ-ONLY", true);
	            
	        } catch (NoSuchMethodException e) {
	        }
	        try {
	        	Method setTimerMethod = Disaster.class.getDeclaredMethod("setTimer", int.class);
	            assertFalse("timer is READ-ONLY", true);
	            
	        } catch (NoSuchMethodException e) {
	        }
	        try {
	        	Method setNameMethod = Disaster.class.getDeclaredMethod("setName", String.class);
	            assertFalse("name is READ-ONLY", true);
	            
	        } catch (NoSuchMethodException e) {
	        }
            
	    }
	 
	 @Test
	    public void B_testSuperCollapse() {
	        assertEquals("Invalid superclass for Collapse", Disaster.class, Collapse.class.getSuperclass());
	    }
	 
	 @Test
	    public void B_testGetDamagedCells() {
	        Collapse collapse = new Collapse();
	        assertEquals("Wrong number of damaged cells", 3, collapse.getDamagedCells());
	    }

	    @Test
	    public void B_testConstructor() {
	        Collapse collapse = new Collapse();
	        assertNotNull("Constructor did not create object", collapse);
	        assertEquals("Wrong collapse name", "Collapse 3", collapse.getName());
	    }
	    
	    @Test
	    public void B_testSetterMethod() {
	        try {
	            Method setIDMethod = Collapse.class.getDeclaredMethod("setGetDamagedCells", int.class);
	            assertFalse("damagedCells is READ-ONLY", true);
	            
	        } catch (NoSuchMethodException e) {
	        }
	    }
	    
	    @Test
		public void B_testPrivateInstanceVariables_Fire() {
			Field[] fields = Fire.class.getDeclaredFields();
			for (Field field : fields) {
				if (!java.lang.reflect.Modifier.isPrivate(field.getModifiers())) {
					fail("Instance variable " + field.getName() + " is not private");
				}
			}
		}
	    
	    @Test
	    public void C_testSuperCollapse() {
	        assertEquals("Invalid superclass for Collapse", Disaster.class, Fire.class.getSuperclass());
	    }
	 
	 @Test
	    public void C_testGetIntesity() {
	        Fire fire = new Fire();
	        assertEquals("Wrong intensity", 20, fire.getIntensity());
	    }

	    @Test
	    public void C_testConstructor() {
	    	Fire fire = new Fire();
	        assertNotNull("Constructor did not create object", fire);
	        assertEquals("Wrong fire name", "Fire 5", fire.getName());
	    }
	    
	    @Test
	    public void C_testSetterMethod() {
	        try {
	            Method setIDMethod = Fire.class.getDeclaredMethod("setIntensity", int.class);
	            assertFalse("intensity is READ-ONLY", true);
	            
	        } catch (NoSuchMethodException e) {
	        }
	    }
	    
	    @Test
		public void C_testPrivateInstanceVariables_Collapse() {
			Field[] fields = Collapse.class.getDeclaredFields();
			for (Field field : fields) {
				if (!java.lang.reflect.Modifier.isPrivate(field.getModifiers())) {
					fail("Instance variable " + field.getName() + " is not private");
				}
			}
		}
	    
	    @Test
	    public void testDisastersPublic() throws NoSuchFieldException, SecurityException {
	        assertFalse("disasters, in game, should be public", Modifier.isPrivate(Game.class.getDeclaredField("disasters").getModifiers()));
	        assertEquals("disasters, in game, should be of type ArrayList<model.world.Disaster>", "java.util.ArrayList<model.world.Disaster>",Game.class.getDeclaredField("disasters").getGenericType().getTypeName());
	        assertTrue("disasters, in game, should be static", Modifier.isStatic(Game.class.getDeclaredField("disasters").getModifiers()));
	    }
}
