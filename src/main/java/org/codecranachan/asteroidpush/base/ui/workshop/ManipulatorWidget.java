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

package org.codecranachan.asteroidpush.base.ui.workshop;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.base.ResourceLoader;
import org.codecranachan.asteroidpush.base.ui.widget.BasicWidget;
import org.codecranachan.asteroidpush.base.visuals.MultipartRepresentation;
import org.codecranachan.asteroidpush.base.visuals.OffsetRepresentation;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.visuals.RepresentationRenderer;
import org.codecranachan.asteroidpush.base.workshop.Blueprint;
import org.codecranachan.asteroidpush.base.workshop.ManipulatedArea;
import org.codecranachan.asteroidpush.base.workshop.WorkshopCoordinator;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Placement;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Token;
import org.codecranachan.asteroidpush.content.visuals.OriginRepresentation;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Circle;
import org.codecranachan.asteroidpush.utils.FieldOfView;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.utils.RectangleMath;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class ManipulatorWidget extends BasicWidget {
   private WorkshopCoordinator coordinator;
   private RepresentationRenderer renderer;

   public ManipulatorWidget(WorkshopCoordinator coordinator,
         ResourceLoader loader) {
      this.coordinator = coordinator;
      this.renderer = new RepresentationRenderer();
   }

   @Override
   public void render(Graphics g) {
      if (getFrame() == null) {
         return;
      }

      if (coordinator.getManipulatedBlueprint() == null) {
         renderNoBlueprint(g);
         return;
      }

      updateRendererFocus();

      g.setAntiAlias(true);
      renderBackground(g);
      renderBlueprint(g);
      renderHover(g);
   }

   private void renderNoBlueprint(Graphics g) {
      g.setColor(Color.red);
      g.drawString("ManipulatorWidget: No blueprint selected", 10, 30);
   }

   private void renderBackground(Graphics g) {
      g.setColor(Color.blue);
      g.fill(getFrame());
   }

   private void renderBlueprint(Graphics g) {
      Rectangle blueprintArea = getBlueprintArea();

      // Draw blueprint frame
      g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
      g.setLineWidth(1.5f);
      g.draw(blueprintArea);

      // Draw blueprint grid
      float tileSize = getTileSize();
      g.setLineWidth(1.5f);
      g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
      for (int i = 1; i < getTilesPerSide(); ++i) {
         float offset = tileSize * i;
         // vertical
         float x = blueprintArea.getX() + offset;
         g.drawLine(x, blueprintArea.getMinY(), x, blueprintArea.getMaxY());
         // horizontal
         float y = blueprintArea.getY() + offset;
         g.drawLine(blueprintArea.getMinX(), y, blueprintArea.getMaxX(), y);
      }

      // Draw tokens and origin
      Blueprint blueprint = coordinator.getManipulatedBlueprint();
      Collection<Token> tokens = blueprint.getTokens();
      List<Representation> representations = new LinkedList<Representation>();
      representations.add(new OriginRepresentation());
      for (Token token : tokens) {
         representations.addAll(token.getRepresentations());
      }
      renderer.setRepresentations(representations);
      renderer.render(g);
   }

   private Rectangle getBlueprintArea() {
      float margin = 10.0f;
      Rectangle square = RectangleMath.getCenteredSquare(getFrame());
      Rectangle area = new Rectangle(square.getX() + margin, square.getY()
            + margin, square.getWidth() - margin * 2, square.getHeight()
            - margin * 2);
      return area;
   }

   private void updateRendererFocus() {
      float magnitude = (float) getTilesPerSide() / 2.0f;

      ManipulatedArea area = coordinator.getManipulatedArea();

      OrthogonalCoordinate offset = area.getBottomLeftCorner();

      float deltaX = MathUtils.floor((float) area.getWidth() / 2.0f);
      float deltaY = MathUtils.floor((float) area.getHeight() / 2.0f);

      if (getTilesPerSide() % 2 == 0) {
         deltaX -= 0.5f;
         deltaY -= 0.5f;
      }

      Vec2 origin = new Vec2(offset.getX() + deltaX, offset.getY() + deltaY);
      FieldOfView fov = new FieldOfView(new Circle(origin, magnitude),
            new Angle());
      renderer.setFieldOfView(fov);
   }

   private void renderHover(Graphics g) {
      renderer.setRepresentations(getHoverRepresentations());
      renderer.render(g);
   }

   private List<Representation> getHoverRepresentations() {
      LinkedList<Representation> list = new LinkedList<Representation>();
      Vector2f hover = getHover();
      Part selected = coordinator.getSelectedPart();
      if (hover != null && selected != null) {
         MultipartRepresentation base = new MultipartRepresentation(3);
         base.addAll(selected.getRepresentations());
         float scale = 1.0f;
         Placement placement = coordinator.getPartSelectedPartPlacement();
         Arrow offset = GeometryConverter.convertToArrow(renderer
               .mapToWorldCoordinates(hover), placement.getOrientation());
         list.add(new OffsetRepresentation(base, offset, scale));

      }
      return list;
   }

   private int getTilesPerSide() {
      ManipulatedArea area = coordinator.getManipulatedArea();
      return area.getLargestSide() + 2;
   }

   private float getTileSize() {
      Rectangle square = getBlueprintArea();
      return square.getHeight() / getTilesPerSide();
   }

   private OrthogonalCoordinate getCoordinateForPoint(Vec2 point) {
      return new OrthogonalCoordinate(Math.round(point.x), Math.round(point.y));
   }

   public void resize(Rectangle frame) {
      setFrame(frame);
      renderer.setFrame(getBlueprintArea());
   }

   public void mousePressed(int button, int x, int y) {
      if (coordinator.getManipulatedBlueprint() == null) {
         return;
      }

      Vec2 world = renderer.mapToWorldCoordinates(new Vector2f(x, y));
      OrthogonalCoordinate coordinate = getCoordinateForPoint(world);

      switch (button) {
      case Input.MOUSE_LEFT_BUTTON:
         coordinator.pickSquare(coordinate);
         break;
      case Input.MOUSE_RIGHT_BUTTON:
         coordinator.clearSquare(coordinate);
         break;
      }
   }
}
