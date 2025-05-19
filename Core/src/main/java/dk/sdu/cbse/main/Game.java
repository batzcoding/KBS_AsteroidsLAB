package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> entityPolygons = new ConcurrentHashMap<>();
    private final Pane rootPane = new Pane();

    private final List<IGamePluginService> pluginServices;
    private final List<IEntityProcessingService> entitySystems;
    private final List<IPostEntityProcessingService> postEntitySystems;

    public Game(List<IGamePluginService> pluginServices,
                List<IEntityProcessingService> entitySystems,
                List<IPostEntityProcessingService> postEntitySystems) {
        this.pluginServices = pluginServices;
        this.entitySystems = entitySystems;
        this.postEntitySystems = postEntitySystems;
    }

    public void start(Stage stage) {
        Text scoreText = new Text(10, 20, "Destroyed asteroids: 0");
        rootPane.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        rootPane.getChildren().add(scoreText);

        Scene scene = new Scene(rootPane);
        setupInputHandling(scene);

        // Start all discovered game plugin services
        pluginServices.forEach(plugin -> plugin.start(gameData, world));

        for (Entity e : world.getEntities()) {
            Polygon p = new Polygon(e.getPolygonCoordinates());
            entityPolygons.put(e, p);
            rootPane.getChildren().add(p);
        }

        stage.setScene(scene);
        stage.setTitle("ASTEROIDS");
        stage.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
                renderEntities();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void updateGame() {
        entitySystems.forEach(system -> system.process(gameData, world));
        postEntitySystems.forEach(system -> system.process(gameData, world));
    }

    private void renderEntities() {
        // Remove stale polygons
        entityPolygons.keySet().removeIf(entity -> {
            if (!world.getEntities().contains(entity)) {
                Polygon p = entityPolygons.get(entity);
                rootPane.getChildren().remove(p);
                return true;
            }
            return false;
        });

        // Update or add polygons for current entities
        for (Entity e : world.getEntities()) {
            Polygon polygon = entityPolygons.computeIfAbsent(e, entity -> {
                Polygon newPoly = new Polygon(entity.getPolygonCoordinates());
                rootPane.getChildren().add(newPoly);
                return newPoly;
            });

            polygon.setTranslateX(e.getX());
            polygon.setTranslateY(e.getY());
            polygon.setRotate(e.getRotation());
        }
    }

    private void setupInputHandling(Scene scene) {
        scene.setOnKeyPressed(event -> toggleKey(event.getCode(), true));
        scene.setOnKeyReleased(event -> toggleKey(event.getCode(), false));
    }

    private void toggleKey(KeyCode code, boolean isPressed) {
        if (code == KeyCode.LEFT) gameData.getKeys().setKey(GameKeys.LEFT, isPressed);
        if (code == KeyCode.RIGHT) gameData.getKeys().setKey(GameKeys.RIGHT, isPressed);
        if (code == KeyCode.UP) gameData.getKeys().setKey(GameKeys.UP, isPressed);
        if (code == KeyCode.SPACE) gameData.getKeys().setKey(GameKeys.SPACE, isPressed);
    }

    public List<IGamePluginService> getGamePluginServices() {
        return pluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entitySystems;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntitySystems;
    }
}
