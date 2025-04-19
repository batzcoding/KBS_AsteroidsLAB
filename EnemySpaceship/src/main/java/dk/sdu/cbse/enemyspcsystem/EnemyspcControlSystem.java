package dk.sdu.cbse.enemyspcsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import java.util.Random;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyspcControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Random random = new Random();

        for (Entity enemy : world.getEntities(Enemyspc.class)) {

            int moveDirection = random.nextInt(4); // 0 = left, 1 = right, 2 = up, 3 = down
            double speed = 2.0; // Adjust speed as needed

            switch (moveDirection) {
                case 0: enemy.setX(enemy.getX() - speed); break; // Move left
                case 1: enemy.setX(enemy.getX() + speed); break; // Move right
                case 2: enemy.setY(enemy.getY() - speed); break; // Move up
                case 3: enemy.setY(enemy.getY() + speed); break; // Move down
            }

            // Prevent enemy from leaving screen
            if (enemy.getX() < 0) enemy.setX(1);
            if (enemy.getX() > gameData.getDisplayWidth()) enemy.setX(gameData.getDisplayWidth() - 1);
            if (enemy.getY() < 0) enemy.setY(1);
            if (enemy.getY() > gameData.getDisplayHeight()) enemy.setY(gameData.getDisplayHeight() - 1);

            if (random.nextDouble() < 0.02) {  // 2% chance per frame to shoot
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData))
                );
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

