package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.appearances.Appearance;
import org.skullforge.asteroidpush.appearances.NullAppearance;
import org.skullforge.asteroidpush.logic.Logic;
import org.skullforge.asteroidpush.parts.Chassis;
import org.skullforge.asteroidpush.testutils.ClassMockery;
import org.skullforge.asteroidpush.ui.Renderer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.jmock.Expectations;

public class DoodadTest {
   ClassMockery context;
   Chassis chassisMock;
   Appearance appearanceMock;
   Logic logicMock;
   Renderer rendererMock;
   Player playerMock;
   SignalController controllerMock;
   World testWorld;
   Doodad testDoodad;

   @Before
   public void setUp() {
      context = new ClassMockery();
      chassisMock = context.mock(Chassis.class);
      appearanceMock = context.mock(Appearance.class);
      rendererMock = context.mock(Renderer.class);
      logicMock = context.mock(Logic.class);
      playerMock = context.mock(Player.class);
      controllerMock = context.mock(SignalController.class);
      Vec2 testGravity = new Vec2();
      testWorld = new World(testGravity, true);
      testDoodad = new Doodad();
   }

   @Test
   public void testSpawnAndDespawnWithoutChassis() {
      context.checking(new Expectations() {
         {
         }
      });

      // Check spawning when there is no chassis defined
      testDoodad.spawn(testWorld);
      assertTrue(testDoodad.isSpawned());
      testDoodad.despawn(testWorld);
      assertFalse(testDoodad.isSpawned());

      context.assertIsSatisfied();
   }

   @Test
   public void testSpawnAndDespawnWithChassis() {
      context.checking(new Expectations() {
         {
            oneOf(chassisMock).spawn(testWorld);
            oneOf(chassisMock).despawn(testWorld);
         }
      });

      testDoodad.setChassis(chassisMock);
      assertFalse(testDoodad.isSpawned());
      testDoodad.spawn(testWorld);
      assertTrue(testDoodad.isSpawned());
      testDoodad.despawn(testWorld);
      assertFalse(testDoodad.isSpawned());

      context.assertIsSatisfied();
   }

   @Test
   public void testUpdateWithLogic() {
      final int expectedFrameNumber = 1;
      context.checking(new Expectations() {
         {
            allowing(playerMock).getController();
            will(returnValue(controllerMock));
            oneOf(logicMock).update(expectedFrameNumber, null);
            oneOf(logicMock).update(expectedFrameNumber, controllerMock);
         }
      });

      testDoodad.setLogic(logicMock);
      testDoodad.update(expectedFrameNumber);
      testDoodad.setOwner(playerMock);
      testDoodad.update(expectedFrameNumber);

      context.assertIsSatisfied();
   }

   @Test
   public void testUpdateWithoutLogic() {
      final int expectedFrameNumber = 1;
      context.checking(new Expectations() {
         {
         }
      });

      testDoodad.update(expectedFrameNumber);

      context.assertIsSatisfied();
   }

   @Test
   public void testRenderWithoutAppearance() {
      context.checking(new Expectations() {
         {
            oneOf(rendererMock).draw(with(any(NullAppearance.class)));
         }
      });

      testDoodad.render(rendererMock);

      context.assertIsSatisfied();
   }

   @Test
   public void testRenderWithAppearance() {
      context.checking(new Expectations() {
         {
            oneOf(rendererMock).draw(appearanceMock);
         }
      });

      testDoodad.setAppearance(appearanceMock);
      testDoodad.render(rendererMock);

      context.assertIsSatisfied();
   }
}
