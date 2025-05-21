package dk.sdu.cbse.common.asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}