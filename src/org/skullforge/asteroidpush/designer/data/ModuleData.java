package org.skullforge.asteroidpush.designer.data;

import java.util.ArrayList;

public class ModuleData {
   private ArrayList<ComponentData> components;
   private String name;

   public ModuleData(String name) {
      this.components = new ArrayList<ComponentData>();
      this.name = name;
   }

   public void addComponent(ComponentData data) {
      this.components.add(data);
   }

   public ArrayList<ComponentData> getComponents() {
      return components;
   }

   public String getName() {
      return name;
   }
}
