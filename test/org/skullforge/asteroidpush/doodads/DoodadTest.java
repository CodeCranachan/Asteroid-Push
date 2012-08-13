package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.appearances.Appearance;
import org.skullforge.asteroidpush.parts.Part;
import org.skullforge.asteroidpush.ui.Renderer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.jmock.Expectations;
import org.jmock.Mockery;

public class DoodadTest {
   Mockery context;
   Part boxMock;
   Part blockMock;
   Appearance appearanceMock;
   Renderer rendererMock;
   World testWorld;
   Doodad testDoodad;

   @Before
   public void setUp() {
      context = new Mockery();
      boxMock = context.mock(Part.class, "Box");
      blockMock = context.mock(Part.class, "Block");
      appearanceMock = context.mock(Appearance.class);
      rendererMock = context.mock(Renderer.class);
      Vec2 testGravity = new Vec2();
      testWorld = new World(testGravity, true);
      testDoodad = new Doodad("TestDoodad");
   }

   @Test
   public void testGetName() {
      assertEquals("TestDoodad", testDoodad.getName());
   }

   @Test
   public void testSpawnAndDespawn() {
      context.checking(new Expectations() {
         {
            oneOf(boxMock).spawn(testWorld);
            oneOf(blockMock).spawn(testWorld);
            oneOf(boxMock).despawn(testWorld);
            oneOf(blockMock).despawn(testWorld);
         }
      });

      testDoodad.addPart(boxMock);
      testDoodad.addPart(blockMock);
      assertFalse(testDoodad.isSpawned());
      testDoodad.spawn(testWorld);
      assertTrue(testDoodad.isSpawned());
      testDoodad.despawn(testWorld);
      assertFalse(testDoodad.isSpawned());

      context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() {
      testDoodad.addPart(boxMock);
      testDoodad.update(1);
      context.assertIsSatisfied();
   }

   @Test
   public void testAddAppearance() {
      context.checking(new Expectations() {
         {
            oneOf(rendererMock).draw(appearanceMock);
         }
      });

      testDoodad.addAppearance(appearanceMock);
      testDoodad.render(rendererMock);

      context.assertIsSatisfied();
   }
}
