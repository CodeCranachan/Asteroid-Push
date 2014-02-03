package org.skullforge.asteroidpush.board;

class Token {
   private TokenShape shape;
   
   public Token() {
      shape = new TokenShape("X");
   }

   public Token(TokenShape shape) {
      this.shape = shape;
   }
   
   public TokenShape getShape() {
      return shape;
   }

}