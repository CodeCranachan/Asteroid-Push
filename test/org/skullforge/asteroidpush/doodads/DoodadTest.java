package org.skullforge.asteroidpush.doodads;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.parts.Part;

public class DoodadTest {
   Mockery context;
   Part partMock;
   Doodad testDoodad;
   
   @Before
   public void setUp() {      
      context = new Mockery();
      partMock = context.mock(Part.class);
      testDoodad = new Doodad();
   }
   
   @Test
   public void testAddPart() {
      testDoodad.addPart(partMock);
   }
}
