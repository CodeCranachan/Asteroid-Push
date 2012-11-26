package org.skullforge.asteroidpush.designer.modules.data;

import java.util.ArrayList;

public class PartData {
   public PartData() {
      subParts = new ArrayList<SubPartData>();
      glue = new GlueData();
   }

   public ArrayList<SubPartData> getSubParts() {
      return subParts;
   }

   public GlueData getGlue() {
      return glue;
   }

   private ArrayList<SubPartData> subParts;
   private GlueData glue;
}
