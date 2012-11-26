package org.skullforge.asteroidpush.designer.grid;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Iterator;

import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.SubModule;
import org.skullforge.asteroidpush.designer.modules.data.PartData;
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
   public void testClear() {
      testGlueMap.clear();
   }

   @Test
   public void testSingleGrouping() {
      Placement firstPlacement = new Placement(0, 0, Facing.FORWARD);
      Placement secondPlacement = new Placement(1, 0, Facing.FORWARD);
      Placement thirdPlacement = new Placement(-1, 0, Facing.FORWARD);
      SubModule firstModule = new SubModule(firstPlacement, new PartData());
      SubModule secondModule = new SubModule(secondPlacement, new PartData());
      SubModule thirdModule = new SubModule(thirdPlacement, new PartData());

      testGlueMap.putPart(firstPlacement, firstModule);
      testGlueMap.putPart(secondPlacement, secondModule);
      testGlueMap.putPart(thirdPlacement, thirdModule);

      Collection<Collection<SubModule>> glueGroups = testGlueMap
            .getGlueGroups();

      assertEquals(1, glueGroups.size());

      Iterator<Collection<SubModule>> groupIterator = glueGroups.iterator();
      assertEquals(3, groupIterator.next().size());
   }
   
   @Test
   public void testMultipleGrouping() {
      Placement firstPlacement = new Placement(0, 1, Facing.FORWARD);
      Placement secondPlacement = new Placement(1, 1, Facing.FORWARD);
      Placement thirdPlacement = new Placement(0, -1, Facing.FORWARD);
      Placement fourthPlacement = new Placement(1, -1, Facing.FORWARD);
      SubModule firstModule = new SubModule(firstPlacement, new PartData());
      SubModule secondModule = new SubModule(secondPlacement, new PartData());
      SubModule thirdModule = new SubModule(thirdPlacement, new PartData());
      SubModule fourthModule = new SubModule(fourthPlacement, new PartData());

      testGlueMap.putPart(firstPlacement, firstModule);
      testGlueMap.putPart(secondPlacement, secondModule);
      testGlueMap.putPart(thirdPlacement, thirdModule);
      testGlueMap.putPart(fourthPlacement, fourthModule);

      Collection<Collection<SubModule>> glueGroups = testGlueMap
            .getGlueGroups();

      assertEquals(2, glueGroups.size());

      Iterator<Collection<SubModule>> groupIterator = glueGroups.iterator();
      assertEquals(2, groupIterator.next().size());
      assertEquals(2, groupIterator.next().size());
   }}
