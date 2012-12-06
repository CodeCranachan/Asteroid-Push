package org.skullforge.asteroidpush.designer.modules.data;

import java.util.ArrayList;
import java.util.Collection;

import org.skullforge.asteroidpush.designer.grid.GridVector;

public class ComponentData {
   final ArrayList<EffectorData> effectors;
   final ArrayList<PrimitiveData> primitives;
   final ArrayList<GridVector> weldDirections;

   public ComponentData() {
      this.effectors = new ArrayList<EffectorData>();
      this.primitives = new ArrayList<PrimitiveData>();
      this.weldDirections = new ArrayList<GridVector>();
   }

   public void addEffector(EffectorData data) {
      effectors.add(data);
   }

   public void addPrimitive(PrimitiveData data) {
      primitives.add(data);
   }

   public void addWeldDirection(GridVector direction) {
      weldDirections.add(direction);
   }

   public Collection<EffectorData> getEffectors() {
      return effectors;
   }

   public Collection<PrimitiveData> getPrimitives() {
      return primitives;
   }

   public Collection<GridVector> getWeldDirections() {
      return weldDirections;
   }
}
