import dk.sdu.cbse.collisionsystem.CollisionDetector;
import dk.sdu.cbse.common.data.Entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    @Test
    void testEntitiesCollideWhenClose() {
        Entity e1 = new Entity();
        e1.setX(0);
        e1.setY(0);
        e1.setRadius(5);

        Entity e2 = new Entity();
        e2.setX(3);  // Within collision range: distance = 3, radius sum = 10
        e2.setY(0);
        e2.setRadius(5);

        CollisionDetector detector = new CollisionDetector();
        assertTrue(detector.collides(e1, e2), "Entities should collide");
    }

    @Test
    void testEntitiesDoNotCollideWhenFar() {
        Entity e1 = new Entity();
        e1.setX(0);
        e1.setY(0);
        e1.setRadius(5);

        Entity e2 = new Entity();
        e2.setX(20);  // Too far: distance = 20, radius sum = 10
        e2.setY(0);
        e2.setRadius(5);

        CollisionDetector detector = new CollisionDetector();
        assertFalse(detector.collides(e1, e2), "Entities should not collide");
    }
}
