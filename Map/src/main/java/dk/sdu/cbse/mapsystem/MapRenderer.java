package dk.sdu.cbse.mapsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MapRenderer implements IPostEntityProcessingService {
    private Pane uiPane;
    private final double mapSize = 150;

    @Override
    public void process(GameData gameData, World world) {
        if (uiPane == null) {
            uiPane = (Pane) gameData.getDisplayData().get("mainPane");
        }

        uiPane.getChildren().removeIf(node -> node instanceof Circle && ((Circle) node).getId() != null && ((Circle) node).getId().startsWith("minimap"));

        for (Entity entity : world.getEntities()) {
            double scaleX = mapSize / gameData.getDisplayWidth();
            double scaleY = mapSize / gameData.getDisplayHeight();

            Circle dot = new Circle(2, Color.YELLOW);
            dot.setId("minimap-" + entity.getID());
            dot.setTranslateX(gameData.getDisplayWidth() - mapSize - 10 + entity.getX() * scaleX);
            dot.setTranslateY(10 + entity.getY() * scaleY);
            uiPane.getChildren().add(dot);
        }
    }
}
