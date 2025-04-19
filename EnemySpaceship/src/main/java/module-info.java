import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemyspc {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.enemyspcsystem.EnemyspcPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemyspcsystem.EnemyspcControlSystem;

}