package org.skullforge.asteroidpush;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.Contact;

public class CollisionObserver implements ContactListener {

   public void beginContact(Contact contact) {
      // Notify collision listeners
   }

   public void endContact(Contact contact) {
      // Notify collision listeners
   }

   public void preSolve(Contact contact, Manifold oldManifold) {
   }

   public void postSolve(Contact contact, ContactImpulse impulse) {
      WorldManifold manifold = new WorldManifold();
      contact.getWorldManifold(manifold);

      assert impulse.count == manifold.points.length;

      for (int i = 0; i < manifold.points.length; ++i) {
         float impactMagnitude = Math.abs(impulse.normalImpulses[i])
               + Math.abs(impulse.tangentImpulses[i]);
         notifyImpact(contact.getFixtureA().getUserData(), manifold.points[i], impactMagnitude);
         notifyImpact(contact.getFixtureB().getUserData(), manifold.points[i], impactMagnitude);
      }
   }

   private void notifyImpact(Object userData, Vec2 worldPoint, float magnitude) {
      if (userData == null) {
         return;
      }

      if (userData instanceof ImpactListener) {
         ImpactListener listener = (ImpactListener) userData;
         listener.handleImpact(worldPoint, magnitude);
      }
   }

}
