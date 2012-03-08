package org.skullforge.omegazone.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.skullforge.omegazone.OmegaZone;

public class OmegaZoneTest {

  private OmegaZone testApp;
  
  @Before
  public void setUp() throws Exception {
    testApp = new OmegaZone();
  }

  @Test
  public void testAppName() throws Exception {
    assertEquals("OmegaZone", testApp.getTitle());
  }
  
  @Test
  public void testStates() throws Exception {
    testApp.initStatesList(null);
    int expectedNumberOfStates = 1;
    assertEquals(expectedNumberOfStates, testApp.getStateCount());
  }
}
