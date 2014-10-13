package org.codecranachan.asteroidpush.base.workshop;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.codecranachan.asteroidpush.base.workshop.assembly.Part;

public class PartSelector {
   private Vector<PartFactory> factories;
   private PartFactory selected;

   public PartFactory getSelected() {
      return selected;
   }

   public Part getNewPart() {
      if (selected == null) {
         return null;
      } else {
         return selected.createPart();
      }
   }

   public void selectNext() {
      if (selected == null) {
         selectByIndex(0);
      } else {
         selectRelative(1);
      }
   }

   public void selectPrevious() {
      if (selected == null) {
         selectByIndex(factories.size() - 1);
      } else {
         selectRelative(-1);
      }
   }

   private void selectRelative(int dir) {
      if (factories.isEmpty()) {
         selected = null;
         return;
      }

      int index = factories.indexOf(selected) + dir;
      if (index > 0) {
         index = index % factories.size();
      } else {
         index = factories.size() - index;
      }
      selectByIndex(index);
   }

   public void selectByIndex(int index) {
      if (index < 0 || index >= factories.size()) {
         selected = null;
      } else {
         selected = factories.get(index);
      }
   }

   public void clearSelection() {
      selected = null;
   }

   public PartSelector() {
      factories = new Vector<PartFactory>();
      selected = null;
   }

   public void addPartFactories(Collection<PartFactory> factories) {
      this.factories.addAll(factories);
      Collections.sort(this.factories, new FactoryComparator());
   }

   public Vector<PartFactory> getPartFactories() {
      return factories;
   }
}

class FactoryComparator implements Comparator<PartFactory> {

   public int compare(PartFactory first, PartFactory second) {
      return String.CASE_INSENSITIVE_ORDER.compare(first.getName(),
                                                   second.getName());
   }

}
