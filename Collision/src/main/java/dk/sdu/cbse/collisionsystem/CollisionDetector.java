package dk.sdu.cbse.collisionsystem;


import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {
    private boolean isBullet(Entity entity) {
        return entity.getClass().getSimpleName().equals("Bullet");
    }

    private boolean isAsteroid(Entity entity) {
        return entity.getClass().getSimpleName().equals("Asteroid");
    }
    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                if (this.collides(entity1, entity2)) {

                    boolean isAsteroidAndBullet =
                            (isAsteroid(entity1) && isBullet(entity2)) ||
                                    (isAsteroid(entity2) && isBullet(entity1));

                    if (isAsteroidAndBullet) {
                        gameData.incrementAsteroidsDestroyed();
                        System.out.println("Asteroids Destroyed: " + gameData.getAsteroidsDestroyed());
                    }

                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
