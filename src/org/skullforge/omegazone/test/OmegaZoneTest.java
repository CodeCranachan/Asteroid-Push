package org.skullforge.omegazone.test;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;
import org.newdawn.slick.state.GameState;
import org.skullforge.omegazone.OmegaZone;
import org.skullforge.omegazone.StateFactory;

public class OmegaZoneTest {
  Mockery context;
  StateFactory factoryMock;
  GameState arenaStateMock;
  OmegaZone testApp;
  
  @Before
  public void setUp() throws Exception {
    context = new Mockery();
    arenaStateMock = context.mock(GameState.class);
    factoryMock = context.mock(StateFactory.class);
    testApp = new OmegaZone(factoryMock);
  }

  @Test
  public void testAppName() throws Exception {
    assertEquals("OmegaZone", testApp.getTitle());
  }
  
  @Test
  public void testStates() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf (factoryMock).createArenaState(); will(returnValue(arenaStateMock));
        oneOf (arenaStateMock).getID(); will(returnValue(1));
      }
    });
    
    testApp.initStatesList(null);
    int expectedNumberOfStates = 1;
    assertEquals(expectedNumberOfStates, testApp.getStateCount());
    
    context.assertIsSatisfied();
  }
}
