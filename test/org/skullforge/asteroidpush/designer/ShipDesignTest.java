package org.skullforge.asteroidpush.designer;

import static org.junit.Assert.*;

import java.util.Collection;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.grid.Rotation;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class ShipDesignTest {
   ClassMockery context;
   Module abacusMock;
   Module bananaMock;
   ShipDesign testDesign;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(Module.class, "AbacusModule");
      bananaMock = context.mock(Module.class, "BananaModule");
      testDesign = new ShipDesign();
   }

   @Test
   public void testGetModules() {
      Collection<Module> modules = testDesign.getModules();
      assertEquals(0, modules.size());
   }

   @Test
   public void testAddModule() {
      final Placement centerPlacement = new Placement();
      final Placement bottomPlacement = new Placement(0, 1, Rotation.BOW);
      
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).setPlacement(with(any(Placement.class)));
            oneOf(bananaMock).setPlacement(with(any(Placement.class)));
         }
      });

      assertTrue(testDesign.canAddModule(centerPlacement, abacusMock));
      testDesign.addModule(centerPlacement, abacusMock);
      assertFalse(testDesign.canAddModule(centerPlacement, bananaMock));
      assertTrue(testDesign.canAddModule(bottomPlacement, bananaMock));
      testDesign.addModule(bottomPlacement, bananaMock);

      Collection<Module> modules = testDesign.getModules();
      assertEquals(2, modules.size());

      context.assertIsSatisfied();
   }
}
