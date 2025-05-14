package dk.sdu.cbse.bulletsystem;


import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                if (checkCollision(entity1, entity2)) {
                    System.out.println("Collision detected between: " +
                            entity1.getClass().getSimpleName() + " and "
                            + entity2.getClass().getSimpleName());
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                    return;


                }

            }
        }

    }
    private boolean checkCollision(Entity a, Entity b) {
        float dx = (float) (a.getX() - b.getX());
        float dy = (float) (a.getY() - b.getY());
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (a.getRadius() + b.getRadius());
    }

}
