package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.simulation.Hull;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.content.visuals.PrimitiveRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;

public class CollisionBehaviorFactory implements BehaviorFactory {

   private Primitive shape;
   private Material material;

   public CollisionBehaviorFactory(Primitive shape, Material material) {
      this.shape = shape;
      this.material = material;
   }

   public Behavior createBehavior(Arrow offset) {
      Hull hull = new Hull(offset, shape, material);
      return new CollisionBehavior(hull);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> represenations = new LinkedList<Representation>();
      represenations.add(new PrimitiveRepresentation(shape));
      return represenations;
   }

}
