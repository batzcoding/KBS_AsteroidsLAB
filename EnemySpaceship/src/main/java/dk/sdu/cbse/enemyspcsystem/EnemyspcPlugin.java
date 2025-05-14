package dk.sdu.cbse.enemyspcsystem;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
public class EnemyspcPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyspcPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        enemy = createEnemySpaceship(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemySpaceship(GameData gameData) {

        Entity EnemySpaceship = new Enemyspc();
        EnemySpaceship.setPolygonCoordinates(-5,-5,10,0,-5,5);
        EnemySpaceship.setX(gameData.getDisplayHeight()/4);
        EnemySpaceship.setY(gameData.getDisplayWidth()/5);
        EnemySpaceship.setRadius(8);
        return EnemySpaceship;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
