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

package org.codecranachan.asteroidpush.content.actors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.OffsetRepresentation;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.FieldOfView;

public class PassiveObject implements Actor {
   private RigidBody body;
   private Representation representation;

   public PassiveObject(RigidBody body, Representation representation) {
      assert body != null;
      assert representation != null;
      this.body = body;
      this.representation = representation;
   }

   public void destroy() {
      body.destroy();
   }

   public Collection<Command> update(int frameNumber) {
      return new LinkedList<Command>();
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> representations = new LinkedList<Representation>();
      representations.add(new OffsetRepresentation(representation, body
            .getState().getState(), 1f));
      return representations;
   }

   public FieldOfView getFieldOfView() {
      return new FieldOfView(body.getEnclosingCircle().addRadius(10f),
            Angle.HALF_PI);
   }

   public void setController(Controller controller) {
      // nothing to do
   }
}
