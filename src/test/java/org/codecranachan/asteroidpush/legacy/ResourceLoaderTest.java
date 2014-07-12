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

package org.codecranachan.asteroidpush.legacy;

import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.legacy.ResourceLoader;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;

public class ResourceLoaderTest {
   Mockery context;
   GameContainer containerMock;
   ResourceLoader testLoader;
   Font fontMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      containerMock = context.mock(GameContainer.class);
      fontMock = context.mock(Font.class);
      testLoader = new ResourceLoader();
   }

   @Test
   public void testLoadFont() {
      context.checking(new Expectations() {
         {
            oneOf(containerMock).getDefaultFont();
            will(returnValue(fontMock));
         }
      });
      
      testLoader.setGameContainer(containerMock);
      assertSame(fontMock, testLoader.loadFont("SomeUnavailableFont.ttf", 14));
      
      context.assertIsSatisfied();
   }
}
