package org.skullforge.asteroidpush.designer;

import static org.junit.Assert.*;

import java.util.Collection;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.ClassMockery;
import org.skullforge.asteroidpush.designer.ShipDesign;

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
      final GridCoordinate centerPosition = new GridCoordinate();
      final GridCoordinate bottomPosition = new GridCoordinate(0, 1);
      
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).setPosition(with(any(GridCoordinate.class)));
            oneOf(bananaMock).setPosition(with(any(GridCoordinate.class)));
         }
      });

      assertTrue(testDesign.canAddModule(centerPosition, abacusMock));
      testDesign.addModule(centerPosition, abacusMock);
      assertFalse(testDesign.canAddModule(centerPosition, bananaMock));
      assertTrue(testDesign.canAddModule(bottomPosition, bananaMock));
      testDesign.addModule(bottomPosition, bananaMock);

      Collection<Module> modules = testDesign.getModules();
      assertEquals(2, modules.size());

      context.assertIsSatisfied();
   }
}
