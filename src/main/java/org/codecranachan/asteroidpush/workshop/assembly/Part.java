package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.workshop.spaceship.Hardpoint;
import org.codecranachan.asteroidpush.workshop.spaceship.Softlink;

public class Part {
   private Collection<Hardpoint> hardpoints;
   private Collection<Softlink> softlinks;
   
   public Part() {
      hardpoints = new LinkedList<Hardpoint>();
      softlinks = new LinkedList<Softlink>();
   }
   
   public void AddHardpoint(Hardpoint hardpoint) {
      hardpoints.add(hardpoint);
   }
   
   public void AddSoftlink(Softlink softlink) {
      softlinks.add(softlink);
   }

   public Collection<Hardpoint> getHardpoints() {
      return hardpoints;
   }

   public Collection<Softlink> getSoftlinks() {
      return softlinks;
   }  
}
