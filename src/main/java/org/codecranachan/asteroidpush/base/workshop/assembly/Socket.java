package org.codecranachan.asteroidpush.base.workshop.assembly;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

public class Socket {
   private Collection<OrthogonalCoordinate> links;

   public Socket() {
      links = new LinkedList<OrthogonalCoordinate>();
   }

   public Collection<OrthogonalCoordinate> getLinks() {
      return links;
   }

   public void addLink(int x, int y) {
      links.add(new OrthogonalCoordinate(x, y));
   }
}