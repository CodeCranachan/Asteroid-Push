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

package org.codecranachan.asteroidpush.visuals.widget;

import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.codecranachan.asteroidpush.visuals.widget.Label;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

public class LabelTest {
   Mockery context;
   Label testLabel;
   String testLabelText = "TestLabel";
   UnicodeFont oldFontMock;
   UnicodeFont labelFontMock;
   Graphics graphicsMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      oldFontMock = context.mock(UnicodeFont.class, "oldFont");
      labelFontMock = context.mock(UnicodeFont.class, "labelFont");
      graphicsMock = context.mock(Graphics.class);
      testLabel = new Label(new StringBuffer(testLabelText), labelFontMock);
   }

   @Test
   public void testRender() {
      final Rectangle frame = new Rectangle(20.0f, 30.0f, 40.0f, 50.0f);
      final Sequence callOrder = context.sequence("callOrder");
      final float textWidth = 75.0f;
      final float textHeight = 15.0f;
      context.checking(new Expectations() {
         {
            allowing(labelFontMock).getHeight(testLabelText);
            will(returnValue((int) textHeight));
            allowing(labelFontMock).getWidth(testLabelText);
            will(returnValue((int) textWidth));

            oneOf(graphicsMock).getFont();
            will(returnValue(oldFontMock));
            inSequence(callOrder);
            oneOf(graphicsMock).setFont(labelFontMock);
            inSequence(callOrder);
            oneOf(graphicsMock).setColor(Color.green);
            inSequence(callOrder);
            oneOf(graphicsMock).drawRoundRect(frame.getX(),
                                              frame.getY(),
                                              frame.getWidth() - 2,
                                              frame.getHeight() - 2,
                                              15);
            inSequence(callOrder);
            oneOf(graphicsMock).drawString(testLabelText,
                                           frame.getCenterX() - textWidth
                                                 / 2.0f,
                                           frame.getCenterY() - textHeight
                                                 / 2.0f);
            inSequence(callOrder);
            oneOf(graphicsMock).setFont(oldFontMock);
            inSequence(callOrder);
         }
      });

      testLabel.resize(frame);
      testLabel.render(graphicsMock);

      context.assertIsSatisfied();
   }
}
