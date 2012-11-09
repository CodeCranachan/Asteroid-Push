package org.skullforge.asteroidpush.doodads;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jbox2d.common.Vec2;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.assemblies.Debris;
import org.skullforge.asteroidpush.testutils.ClassMockery;
public class AsteroidFactoryTest {
   ClassMockery context;
   Doodad doodadMock;
   AsteroidFactory testFactory;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      doodadMock = context.mock(Doodad.class);
      testFactory = new AsteroidFactory();
   }

   @Test
   public void testCreateDoodad() {
      Doodad testDoodad = testFactory.createDoodad();
      assertNotNull(testDoodad);
   }

   @Test
   public void testInitDoodad() {
      final Vec2 position = new Vec2(10.0f, 5.0f);
      context.checking(new Expectations() {
         {
            oneOf(doodadMock).setAssembly(with(any(Debris.class)));
            oneOf(doodadMock).setAppearance(with(any(SimpleAppearance.class)));
         }
      });

      testFactory.setParameters(position);
      assertEquals(doodadMock, testFactory.initDoodad(doodadMock));

      context.assertIsSatisfied();
   }
}
