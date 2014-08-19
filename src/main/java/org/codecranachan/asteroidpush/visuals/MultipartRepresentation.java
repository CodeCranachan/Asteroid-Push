package org.codecranachan.asteroidpush.visuals;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;

public class MultipartRepresentation implements Representation {
   private List<Representation> representations;
   private int priority;

   public MultipartRepresentation(int priority) {
      this.representations = new LinkedList<Representation>();
      this.priority = priority;
   }

   public void add(Representation representation) {
      assert representation != null;
      representations.add(representation);
      Collections.sort(representations, new RepresentationComparator());
   }

   public void addAll(Collection<Representation> representations) {
      assert representations != null;
      this.representations.addAll(representations);
      Collections.sort(this.representations, new RepresentationComparator());
   }

   public void render(Graphics g) {
      for (Representation rep : representations) {
         rep.render(g);
      }
   }

   public int getPriority() {
      return priority;
   }

}
