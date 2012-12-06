package org.skullforge.asteroidpush.designer;

import static org.junit.Assert.*;

import java.util.Collection;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.data.ModuleData;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class BlueprintTest {
   ClassMockery context;
   ModuleData abacusMock;
   ModuleData bananaMock;
   Blueprint testDesign;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(ModuleData.class, "AbacusModule");
      bananaMock = context.mock(ModuleData.class, "BananaModule");
      testDesign = new Blueprint();
   }

   @Test
   public void testGetTokens() {
      Collection<ModuleToken> tokens = testDesign.getTokens();
      assertEquals(0, tokens.size());
   }

   @Test
   public void testAddModule() {
      final Placement centerPlacement = new Placement();
      final Placement bottomPlacement = new Placement(0, 1, Facing.FORWARD);

      context.checking(new Expectations() {
         {
         }
      });

      assertTrue(testDesign.canAddModule(centerPlacement, null));
      testDesign.addModule(centerPlacement, abacusMock);
      assertFalse(testDesign.canAddModule(centerPlacement, null));
      assertTrue(testDesign.canAddModule(bottomPlacement, null));
      testDesign.addModule(bottomPlacement, bananaMock);

      Collection<ModuleToken> modules = testDesign.getTokens();
      assertEquals(2, modules.size());

      context.assertIsSatisfied();
   }
}
