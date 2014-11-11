package org.codecranachan.asteroidpush.base.workshop.assembly;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Placeable;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;

public class Part implements Placeable {
   private Collection<Component> components;
   private Shape shape;

   // Should be a bunch of factories and the coordinates to attach them to

   public Part(Shape shape) {
      assert shape != null;
      this.components = new LinkedList<Component>();
      this.shape = shape;
   }

   public void addComponent(Component component) {
      components.add(component);
   }

   public Shape getShape() {
      return shape;
   }

   public Collection<Component> getComponents() {
      return components;
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> representations = new LinkedList<Representation>();
      for (Component component : components) {
         if (component.getRepresentations() != null) {
            representations.addAll(component.getRepresentations());
         }
      }
      return representations;
   }
}
