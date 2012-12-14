package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.dynamics.Body;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.designer.data.effectors.TorqueFeederData;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class TorqueFeederTest {

   ClassMockery context;
   Body bodyMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      bodyMock = context.mock(Body.class);
   }

   @Test
   public void testUpdate() {
      final float expectedTorque = 1000.0f;

      SignalController testController = new SignalController();
      TorqueFeederData data = new TorqueFeederData();
      data.setMagnitude(expectedTorque);
      TorqueFeeder testFeeder = new TorqueFeeder(data);
      testFeeder.setPropulsee(bodyMock);

      // Body can not be mocked due to excessive use of 'final'
      // so we can only check if the call to update does not crash... 
      testFeeder.update(0, testController);
      testFeeder.update(0, null);
   }
}
