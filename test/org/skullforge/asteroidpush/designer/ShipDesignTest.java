package org.skullforge.asteroidpush.designer;

import static org.junit.Assert.*;

import java.util.Collection;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.data.ModuleData;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class ShipDesignTest {
   ClassMockery context;
   ModuleData abacusMock;
   ModuleData bananaMock;
   ShipDesign testDesign;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(ModuleData.class, "AbacusModule");
      bananaMock = context.mock(ModuleData.class, "BananaModule");
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
      final Placement bottomPlacement = new Placement(0, 1, Facing.FORWARD);
      
      context.checking(new Expectations() {
         {
         }
      });

      assertTrue(testDesign.canAddModule(centerPlacement));
      testDesign.addModule(centerPlacement, abacusMock);
      assertFalse(testDesign.canAddModule(centerPlacement));
      assertTrue(testDesign.canAddModule(bottomPlacement));
      testDesign.addModule(bottomPlacement, bananaMock);

      Collection<Module> modules = testDesign.getModules();
      assertEquals(2, modules.size());

      context.assertIsSatisfied();
   }
}
