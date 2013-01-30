package org.skullforge.asteroidpush;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public class CollisionObserver implements ContactListener {

   @Override
   public void beginContact(Contact contact) {
      // Notify collision listeners
   }

   @Override
   public void endContact(Contact contact) {
      // Notify collision listeners
   }

   @Override
   public void preSolve(Contact contact, Manifold oldManifold) {
   }

   @Override
   public void postSolve(Contact contact, ContactImpulse impulse) {
      notifyImpact(contact.getFixtureA().getUserData());
      notifyImpact(contact.getFixtureB().getUserData());
   }

   private void notifyImpact(Object userData) {
      if (userData == null) {
         return;
      }

      if (userData instanceof ImpactListener) {
         ImpactListener listener = (ImpactListener) userData;
         listener.handleImpact();
      }
   }

}
