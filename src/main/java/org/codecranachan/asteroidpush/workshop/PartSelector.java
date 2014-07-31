package org.codecranachan.asteroidpush.workshop;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.codecranachan.asteroidpush.workshop.parts.TokenFactory;

public class PartSelector {
   private Vector<TokenFactory> factories;
   private TokenFactory selected;

   public TokenFactory getSelected() {
      return selected;
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
      factories = new Vector<TokenFactory>();
      selected = null;
   }

   public void addPartFactories(Collection<TokenFactory> factories) {
      this.factories.addAll(factories);
      Collections.sort(this.factories, new FactoryComparator());
   }

   public Collection<TokenFactory> getPartFactories() {
      return factories;
   }
}

class FactoryComparator implements Comparator<TokenFactory> {

   public int compare(TokenFactory first, TokenFactory second) {
      return String.CASE_INSENSITIVE_ORDER.compare(first.getName(),
                                                   second.getName());
   }

}
