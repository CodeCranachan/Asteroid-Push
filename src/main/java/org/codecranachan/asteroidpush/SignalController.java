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

package org.codecranachan.asteroidpush;

import org.newdawn.slick.Input;

public class SignalController {

   public SignalController() {
      leftThrust = 0.0f;
      rightThrust = 0.0f;
      forwardThrust = 0.0f;
      backwardThrust = 0.0f;
      anticlockwiseThrust = 0.0f;
      clockwiseThrust = 0.0f;
      primaryFire = 0.0f;
      secondaryFire = 0.0f;
   }

   void keyDown(int key) {
      switch (key) {
      case Input.KEY_W:
         forwardThrust = 1.0f;
         break;
      case Input.KEY_S:
         backwardThrust = 1.0f;
         break;
      case Input.KEY_A:
         anticlockwiseThrust = 1.0f;
         break;
      case Input.KEY_D:
         clockwiseThrust = 1.0f;
         break;
      case Input.KEY_Q:
         leftThrust = 1.0f;
         break;
      case Input.KEY_E:
         rightThrust = 1.0f;
         break;
      case Input.KEY_RSHIFT:
         primaryFire = 1.0f;
         break;
      case Input.KEY_RCONTROL:
         secondaryFire = 1.0f;
         break;
      default:
         break;
      }
   }

   void keyUp(int key) {
      switch (key) {
      case Input.KEY_W:
         forwardThrust = 0.0f;
         break;
      case Input.KEY_S:
         backwardThrust = 0.0f;
         break;
      case Input.KEY_A:
         anticlockwiseThrust = 0.0f;
         break;
      case Input.KEY_D:
         clockwiseThrust = 0.0f;
         break;
      case Input.KEY_Q:
         leftThrust = 0.0f;
         break;
      case Input.KEY_E:
         rightThrust = 0.0f;
         break;
      case Input.KEY_RSHIFT:
         primaryFire = 0.0f;
         break;
      case Input.KEY_RCONTROL:
         secondaryFire = 0.0f;
         break;
      default:
         break;
      }
   }

   public String getControllerDescription() {
      StringBuilder buffer = new StringBuilder();

      if (leftThrust > 0.0f) {
         buffer.append("L");
      }
      if (rightThrust > 0.0f) {
         buffer.append("R");
      }
      if (forwardThrust > 0.0f) {
         buffer.append("F");
      }
      if (backwardThrust > 0.0f) {
         buffer.append("B");
      }
      if (anticlockwiseThrust > 0.0f) {
         buffer.append("A");
      }
      if (clockwiseThrust > 0.0f) {
         buffer.append("C");
      }
      if (primaryFire > 0.0f) {
         buffer.append("1");
      }
      if (secondaryFire > 0.0f) {
         buffer.append("2");
      }
      return buffer.toString();
   }

   public float leftThrust;
   public float rightThrust;
   public float forwardThrust;
   public float backwardThrust;
   public float anticlockwiseThrust;
   public float clockwiseThrust;
   public float primaryFire;
   public float secondaryFire;
}
