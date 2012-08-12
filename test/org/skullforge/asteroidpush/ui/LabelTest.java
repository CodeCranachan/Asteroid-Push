package org.skullforge.asteroidpush.ui;

import static org.junit.Assert.*;

import java.awt.Font;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.ClassMockery;

public class LabelTest {
   Mockery context;
   Label testLabel;
   String testLabelText = "TestLabel";
   UnicodeFont testLabelFont = new UnicodeFont(new Font("SansSerif", Font.PLAIN, 12));
   Graphics graphicsMock;
   float labelX = 23.0f;
   float labelY = 48.0f;

   @Before
   public void setUp() throws Exception {
      testLabel = new Label(labelX, labelY, testLabelText, testLabelFont);
      context = new ClassMockery();
      graphicsMock = context.mock(Graphics.class);
   }

   @Test
   public void testBoundingBox() {
      Rectangle boundingBox = testLabel.getBoundingBox();
      assertEquals(labelX, boundingBox.getX(), 0.0);
      assertEquals(labelY, boundingBox.getY(), 0.0);
   }

   @Test
   public void testRender() {
      final UnicodeFont somePreviousFont = new UnicodeFont(new Font("Serif", Font.BOLD, 10));

      context.checking(new Expectations() {
         {
            oneOf(graphicsMock).scale(with(any(Float.class)), with(any(Float.class)));
            oneOf(graphicsMock).getFont();
            will(returnValue(somePreviousFont));
            oneOf(graphicsMock).setFont(testLabelFont);
            oneOf(graphicsMock).drawString(testLabelText, labelX, labelY);
            oneOf(graphicsMock).setFont(somePreviousFont);
            oneOf(graphicsMock).resetTransform();
         }
      });

      testLabel.render(graphicsMock);

      context.assertIsSatisfied();
   }
}
