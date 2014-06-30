package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.Collection;
import java.util.LinkedList;

public class Part {
   private Collection<AssemblyVertex> hardpoints;
   private Collection<DynamicConnector> softlinks;
   
   public Part() {
      hardpoints = new LinkedList<AssemblyVertex>();
      softlinks = new LinkedList<DynamicConnector>();
   }
   
   public void AddHardpoint(AssemblyVertex hardpoint) {
      hardpoints.add(hardpoint);
   }
   
   public void AddSoftlink(DynamicConnector softlink) {
      softlinks.add(softlink);
   }

   public Collection<AssemblyVertex> getHardpoints() {
      return hardpoints;
   }

   public Collection<DynamicConnector> getSoftlinks() {
      return softlinks;
   }  
}
