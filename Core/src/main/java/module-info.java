module Core {
    opens dk.sdu.cbse.main to javafx.graphics;
    requires Common;
    requires javafx.graphics;
    requires CommonBullet;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}

