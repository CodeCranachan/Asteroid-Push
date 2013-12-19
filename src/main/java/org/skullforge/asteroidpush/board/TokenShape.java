package org.skullforge.asteroidpush.board;

import java.util.Vector;

class TokenShape {
   private Vector<String> form_;

   public TokenShape(String... form) {
      form_ = new Vector<String>();
      
      if (form.length == 0) {
         form_.add("X");
      }
      
      for (String line : form) {
         if (!line.contains("X")) {
            throw new IllegalArgumentException("invalid form strings passed");
         } else {
            form_.add(line);
         }
      }
   }

   public boolean equals(Object obj) {
      if (obj == null)
         return false;
      if (obj.getClass() != TokenShape.class)
         return false;

      TokenShape other = (TokenShape) obj;
      if (other.form_.equals(this.form_))
         return true;
      else
         return false;
   }
}