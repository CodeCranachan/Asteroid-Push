package org.skullforge.asteroidpush.designer.data;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class ModuleData {
   private ArrayList<ComponentData> components;
   private ArrayList<JointData> joints;
   private String name;

   public ModuleData(String name) {
      this.components = new ArrayList<ComponentData>();
      this.joints = new ArrayList<JointData>();
      this.name = name;
   }

   public void addComponent(ComponentData data) {
      this.components.add(data);
   }

   public void addJoint(JointData data) {
      this.joints.add(data);
   }

   public Collection<ComponentData> getComponents() {
      return components;
   }

   public Collection<JointData> getJoints() {
      return joints;
   }

   public String getName() {
      return name;
   }

   public Collection<Shape> getOutline() {
      Collection<Shape> outline = new ArrayList<Shape>();

      for (ComponentData componentData : components) {
         for (PrimitiveData primitive : componentData.getPrimitives()) {
            float points[] = primitive.getPointArray();
            Polygon poly = new Polygon(points);
            outline.add(poly);
         }
      }

      return outline;
   }
}
