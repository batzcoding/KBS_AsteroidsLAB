module Map {
    requires Common;
    requires javafx.graphics;

    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.mapsystem.MapPlugin;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.mapsystem.MapRenderer;
}
