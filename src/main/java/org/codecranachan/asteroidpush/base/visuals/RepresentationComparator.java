package org.codecranachan.asteroidpush.base.visuals;

import java.util.Comparator;

public class RepresentationComparator implements Comparator<Representation> {

   public int compare(Representation first, Representation second) {
      return first.getPriority() - second.getPriority();
   }

}