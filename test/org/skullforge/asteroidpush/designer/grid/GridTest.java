package org.skullforge.asteroidpush.designer.grid;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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

      GridVector firstPos = new GridVector(2, -2);
      Integer firstValue = 1;
      GridVector secondPos = new GridVector(-2, 2);
      Integer secondValue = 2;

      testGrid.put(firstPos, firstValue);
      testGrid.put(secondPos, secondValue);

      GridVector expectedMin = new GridVector(-2, -2);
      GridVector expectedMax = new GridVector(2, 2);

      assertThat(testGrid.min(), equalTo(expectedMin));
      assertThat(testGrid.max(), equalTo(expectedMax));
   }

}
