package org.skullforge.asteroidpush.doodads;

import static org.junit.Assert.*;

import org.jbox2d.common.Vec2;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.ClassMockery;
import org.skullforge.asteroidpush.parts.StaticBox;

public class PlayingFieldBorderFactoryTest {
   ClassMockery context;
   Doodad doodadMock;
   PlayingFieldBorderFactory testFactory;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      doodadMock = context.mock(Doodad.class);
      testFactory = new PlayingFieldBorderFactory();
   }

   @Test
   public void testCreateDoodad() {
      Doodad testDoodad = testFactory.createDoodad();
      assertNotNull(testDoodad);
      assertEquals("PlayingFieldBorder", testDoodad.getName());
   }

   @Test
   public void testInitDoodad() {
      float width = 330.0f;
      float height = 1024.0f;
      Vec2 expectedInnerDiagonal = new Vec2(width / 2.0f, height / 2.0f);
      Vec2 expectedThickness = new Vec2(10.0f, 10.0f);
      Vec2 expectedOuterDiagonal = expectedInnerDiagonal.add(expectedThickness);
      final StaticBox expectedBox = new StaticBox(expectedInnerDiagonal,
            expectedOuterDiagonal);

      context.checking(new Expectations() {
         {
            oneOf(doodadMock).addPart(with(equal(expectedBox)));
         }
      });

      testFactory.setParameters(width, height);
      assertEquals(doodadMock, testFactory.initDoodad(doodadMock));

      context.assertIsSatisfied();
   }
}
