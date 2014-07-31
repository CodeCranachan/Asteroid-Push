package org.codecranachan.asteroidpush.workshop.parts;

import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.assembly.AssemblyVertex;
import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;
import org.jbox2d.common.Vec2;

public class Block implements TokenFactory {

   public String getName() {
      return "Block";
   }

   public Token<Part> createToken() {
      return new Token<Part>(getShape(), createPart());
   }

   private Shape getShape() {
      return new Shape("X");
   }

   private Part createPart() {
      Part part = new Part();

      AssemblyVertex block = new AssemblyVertex();
      Primitive shape = new Primitive();
      shape.AddSquare(new Arrow(new Vec2(0, 0), 0f, 0.5f));
      CollisionBehaviorFactory factory = new CollisionBehaviorFactory(shape,
            Material.METAL);
      factory.setParent(block);
      block.AddBehaviorFactory(factory);
      block.AddHardLink(new OrthogonalCoordinate(-1, 0));
      block.AddHardLink(new OrthogonalCoordinate(0, -1));
      block.AddHardLink(new OrthogonalCoordinate(1, 0));
      block.AddHardLink(new OrthogonalCoordinate(0, 1));
      part.AddHardpoint(block);

      return part;
   }

}
