package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.codecranachan.asteroidpush.visuals.widget.Label;
import org.codecranachan.asteroidpush.visuals.widget.Widget;
import org.codecranachan.asteroidpush.workshop.BlueprintCollection;
import org.codecranachan.asteroidpush.workshop.Manipulator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class WorkshopUi extends BasicWidget {
   private WorkshopUiLayout layout;
   private Manipulator manipulator;
   private BlueprintCollection collection;
   private CreateBlueprintButton createBlueprintButton;
   private Widget manipulatorWidget;

   public WorkshopUi(ResourceLoader loader) {
      layout = new WorkshopUiLayout();
      manipulator = new Manipulator();
      collection = null;

      manipulatorWidget = new ManipulatorWidget(manipulator, loader);
      createBlueprintButton = new CreateBlueprintButton(
            loader.loadFont("resources/Alphabet-IV.tty", 15));
      updateBlueprintWidget();

      layout.setCatalogueWidget(new BasicWidget());
      layout.setSelectionWidget(new BasicWidget());
      // TODO Create collection view
      // TODO Create selection view
      // TODO Create catalogue view
   }

   public void setManipulator(Manipulator manipulator) {
      this.manipulator = manipulator;
   }

   public void setCollection(BlueprintCollection collection) {
      this.collection = collection;
      manipulator.setBlueprint(collection.getActive());
      createBlueprintButton.setCollection(collection);
   }

   public void resize(Rectangle frame) {
      updateBlueprintWidget();
      layout.resize(frame);
   }

   public void render(Graphics g) {
      layout.render(g);
   }

   public void setHover(float x, float y) {
      layout.setHover(x, y);
   }

   public void resetHover() {
      layout.resetHover();
   }

   public void mousePressed(int button, int x, int y) {
      layout.mousePressed(button, x, y);
   }

   private void updateBlueprintWidget() {
      if (collection != null) {
         manipulator.setBlueprint(collection.getActive());
      }
      
      if (manipulator.getBlueprint() == null) {
         layout.setBlueprintWidget(createBlueprintButton);
      } else {
         layout.setBlueprintWidget(manipulatorWidget);
      }
   }
}
