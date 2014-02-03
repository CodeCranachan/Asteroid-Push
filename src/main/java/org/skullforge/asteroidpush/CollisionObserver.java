//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
