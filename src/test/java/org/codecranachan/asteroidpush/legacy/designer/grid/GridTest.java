//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.legacy.designer.grid;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.legacy.designer.grid.Grid;
import org.codecranachan.asteroidpush.legacy.designer.grid.GridVector;
import org.junit.Test;

public class GridTest {

   @Test
   public void testPuttingAndGetting() {
      Grid<Integer> testGrid = new Grid<Integer>();

      GridVector firstPos = new GridVector(2, -2);
      Integer firstValue = 1;
      GridVector secondPos = new GridVector(-2, 2);
      Integer secondValue = 2;
      GridVector thirdPos = new GridVector(1, 1);

      testGrid.put(firstPos, firstValue);
      testGrid.put(secondPos, secondValue);

      assertThat(testGrid.get(firstPos), sameInstance(firstValue));
      assertThat(testGrid.get(secondPos), sameInstance(secondValue));
      assertThat(testGrid.get(thirdPos), nullValue());

      testGrid.put(secondPos, firstValue);

      assertThat(testGrid.get(secondPos), sameInstance(firstValue));
   }

   @Test
   public void testRemoving() {
      Grid<Integer> testGrid = new Grid<Integer>();

      GridVector pos = new GridVector(5, 5);
      Integer value = 1;
      testGrid.put(pos, value);

      assertThat(testGrid.get(pos), sameInstance(value));
      testGrid.remove(pos);

      assertThat(testGrid.get(pos), nullValue());
      testGrid.remove(pos);
   }

   @Test
   public void testBoundaryDetection() {
      Grid<Integer> testGrid = new Grid<Integer>();

      assertThat(testGrid.getMin(), equalTo(new GridVector()));
      assertThat(testGrid.getMax(), equalTo(new GridVector()));

      GridVector firstPos = new GridVector(2, -2);
      Integer firstValue = 1;
      GridVector secondPos = new GridVector(-2, 2);
      Integer secondValue = 2;

      testGrid.put(firstPos, firstValue);
      testGrid.put(secondPos, secondValue);

      GridVector expectedMin = new GridVector(-2, -2);
      GridVector expectedMax = new GridVector(2, 2);

      assertThat(testGrid.getMin(), equalTo(expectedMin));
      assertThat(testGrid.getMax(), equalTo(expectedMax));
   }

}
