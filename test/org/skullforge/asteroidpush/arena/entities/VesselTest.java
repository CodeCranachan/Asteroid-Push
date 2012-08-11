package org.skullforge.asteroidpush.arena.entities;

import org.junit.*;
import org.newdawn.slick.Image;
import org.skullforge.asteroidpush.ClassMockery;
import org.skullforge.asteroidpush.arena.SignalTracker;
import org.skullforge.asteroidpush.arena.entities.Vessel;

public class VesselTest {
   private ClassMockery context;
   private Vessel testVessel;
   private Image imageMock;
   private SignalTracker trackerMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      imageMock = context.mock(Image.class);
      testVessel = new Vessel(imageMock);
      trackerMock = context.mock(SignalTracker.class);
   }

   @Test
   public void testRendering() throws Exception {
      // Jmock is unable to mock any classes with final methods, so we can not
      // test rendering without building a complete simulation.
   }

   @Test
   public void testSpawning() throws Exception {
      // Jmock is unable to mock any classes with final methods, so we can not
      // test spawning without building a complete simulation.
   }

   @Test
   public void testUpdating() throws Exception {
      testVessel.update(0, trackerMock);
   }

}
