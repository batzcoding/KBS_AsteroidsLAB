import dk.sdu.cbse.asteroid.EntityProcessor;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteriods {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidProcessor, EntityProcessor;
}