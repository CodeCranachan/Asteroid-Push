package org.skullforge.asteroidpush.designer.modules.data;

import java.util.ArrayList;

public class PartData {
   public PartData() {
      subParts = new ArrayList<SubPartData>();
      effectorFactories = new ArrayList<EffectorFactory>();
      glue = new GlueData();
   }

   public ArrayList<SubPartData> getSubParts() {
      return subParts;
   }

   public ArrayList<EffectorFactory> getEffectorFactories() {
      return effectorFactories;
   }
   
   public GlueData getGlue() {
      return glue;
   }

   private ArrayList<SubPartData> subParts;
   private ArrayList<EffectorFactory> effectorFactories;
   private GlueData glue;
}
