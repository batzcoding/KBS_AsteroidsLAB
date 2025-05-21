package dk.sdu.cbse.mapsystem;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MapPlugin implements IGamePluginService {
    private Rectangle mapBorder;
    private final double mapSize = 150;
    private Pane uiPane;

    @Override
    public void start(GameData gameData, World world) {
        uiPane = (Pane) gameData.getDisplayData().get("mainPane");
        mapBorder = new Rectangle(mapSize, mapSize);
        mapBorder.setStroke(Color.WHITE);
        mapBorder.setFill(Color.rgb(20, 20, 20, 0.5));
        mapBorder.setTranslateX(gameData.getDisplayWidth() - mapSize - 10);
        mapBorder.setTranslateY(10);
        uiPane.getChildren().add(mapBorder);
    }

    @Override
    public void stop(GameData gameData, World world) {
        uiPane.getChildren().remove(mapBorder);
    }
}
