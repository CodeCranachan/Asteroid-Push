package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.codecranachan.asteroidpush.visuals.widget.Widget;
import org.codecranachan.asteroidpush.workshop.BlueprintCollection;
import org.codecranachan.asteroidpush.workshop.Manipulator;
import org.codecranachan.asteroidpush.workshop.PartSelector;
import org.codecranachan.asteroidpush.workshop.parts.DefaultCatalogue;
import org.codecranachan.asteroidpush.workshop.parts.PartCatalogue;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class WorkshopUi extends BasicWidget {
   private WorkshopUiLayout layout;
   private WorkshopCoordinator coordinator;
   private CreateBlueprintButton createBlueprintButton;
   private Widget manipulatorWidget;

   public WorkshopUi(BlueprintCollection collection, ResourceLoader loader) {
      layout = new WorkshopUiLayout();
      coordinator = createCoordinator(collection);

      manipulatorWidget = new ManipulatorWidget(coordinator, loader);
      createBlueprintButton = new CreateBlueprintButton(coordinator,
            loader.loadFont("resources/Alfphabet-IV.ttf", 15));
      updateBlueprintWidget();

      layout.setCatalogueWidget(new SelectorWidget(coordinator, loader.loadFont("resources/Alfphabet-IV.ttf", 15)));
      layout.setSelectionWidget(new BasicWidget());
   }

   private WorkshopCoordinator createCoordinator(BlueprintCollection collection) {
      Manipulator manipulator = new Manipulator();
      PartSelector selector = new PartSelector();

      PartCatalogue catalogue = new DefaultCatalogue();
      selector.addPartFactories(catalogue.getPartFactories());

      return new WorkshopCoordinator(manipulator, selector, collection);
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
      if (coordinator.getManipulatedBlueprint() == null) {
         layout.setBlueprintWidget(createBlueprintButton);
      } else {
         layout.setBlueprintWidget(manipulatorWidget);
      }
   }
}
