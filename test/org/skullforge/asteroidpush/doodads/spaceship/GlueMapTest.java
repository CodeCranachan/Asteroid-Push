package org.skullforge.asteroidpush.doodads.spaceship;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Iterator;

import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;

import org.skullforge.asteroidpush.testutils.ClassMockery;

public class GlueMapTest {
   Mockery context;
   GlueMap testGlueMap;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      testGlueMap = new GlueMap();
   }

   @Test
   public void testSingleGrouping() {
      Placement firstPlacement = new Placement(0, 0, Facing.FORWARD);
      Placement secondPlacement = new Placement(1, 0, Facing.FORWARD);
      Placement thirdPlacement = new Placement(-1, 0, Facing.FORWARD);

      ComponentData weldsInAllDirections = new ComponentData();
      weldsInAllDirections.addWeldDirection(GridVector.FORWARD);
      weldsInAllDirections.addWeldDirection(GridVector.BACK);
      weldsInAllDirections.addWeldDirection(GridVector.LEFT);
      weldsInAllDirections.addWeldDirection(GridVector.RIGHT);

      Part firstPart = new Part(firstPlacement, weldsInAllDirections);
      Part secondPart = new Part(secondPlacement, weldsInAllDirections);
      Part thirdPart = new Part(thirdPlacement, weldsInAllDirections);

      testGlueMap.putPart(firstPart);
      testGlueMap.putPart(secondPart);
      testGlueMap.putPart(thirdPart);

      Collection<Collection<Part>> glueGroups = testGlueMap.getGlueGroups();

      assertEquals(1, glueGroups.size());

      Iterator<Collection<Part>> groupIterator = glueGroups.iterator();
      assertEquals(3, groupIterator.next().size());

      testGlueMap.clear();
   }

   @Test
   public void testMultipleGrouping() {
      Placement firstPlacement = new Placement(0, 1, Facing.FORWARD);
      Placement secondPlacement = new Placement(1, 1, Facing.FORWARD);
      Placement thirdPlacement = new Placement(0, -1, Facing.FORWARD);
      Placement fourthPlacement = new Placement(1, -1, Facing.FORWARD);

      ComponentData weldsInAllDirections = new ComponentData();
      weldsInAllDirections.addWeldDirection(GridVector.FORWARD);
      weldsInAllDirections.addWeldDirection(GridVector.BACK);
      weldsInAllDirections.addWeldDirection(GridVector.LEFT);
      weldsInAllDirections.addWeldDirection(GridVector.RIGHT);

      Part firstPart = new Part(firstPlacement, weldsInAllDirections);
      Part secondPart = new Part(secondPlacement, weldsInAllDirections);
      Part thirdPart = new Part(thirdPlacement, weldsInAllDirections);
      Part fourthPart = new Part(fourthPlacement, weldsInAllDirections);

      testGlueMap.putPart(firstPart);
      testGlueMap.putPart(secondPart);
      testGlueMap.putPart(thirdPart);
      testGlueMap.putPart(fourthPart);

      Collection<Collection<Part>> glueGroups = testGlueMap.getGlueGroups();

      assertEquals(2, glueGroups.size());

      Iterator<Collection<Part>> groupIterator = glueGroups.iterator();
      assertEquals(2, groupIterator.next().size());
      assertEquals(2, groupIterator.next().size());

      testGlueMap.clear();
   }
}
