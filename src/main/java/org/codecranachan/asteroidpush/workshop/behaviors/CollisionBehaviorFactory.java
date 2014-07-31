package org.codecranachan.asteroidpush.workshop.behaviors;

import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;
import org.codecranachan.asteroidpush.simulation.modular.BehaviorFactory;
import org.codecranachan.asteroidpush.simulation.modular.BodyVertex;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.codecranachan.asteroidpush.visuals.behaviors.PrimitiveRepresentation;
import org.codecranachan.asteroidpush.workshop.assembly.AssemblyVertex;

public class CollisionBehaviorFactory implements BehaviorFactory {

   private Primitive shape;
   private Material material;
   private AssemblyVertex parentNode;

   public CollisionBehaviorFactory(Primitive shape, Material material) {
      this.shape = shape;
      this.material = material;
      this.parentNode = null;
   }

   public void setParent(AssemblyVertex node) {
      this.parentNode = node;
   }

   public List<AssemblyVertex> getNodes() {
      List<AssemblyVertex> nodes = new LinkedList<AssemblyVertex>();
      nodes.add(parentNode);
      return nodes;
   }

   public Behavior createBehavior(List<BodyVertex> nodes) {
      Arrow offset = nodes.get(0).getPlacement();
      Hull hull = new Hull(offset, shape, material);
      return new CollisionBehavior(nodes.get(0), hull);
   }

   public Representation getRepresentation() {
      return new PrimitiveRepresentation(shape, getNodes().get(0)
            .getPlacement());
   }

}
