package dk.sdu.cbse.main;
import java.lang.module.*;
import java.nio.file.Path;
import java.util.Set;
public class PluginLoader {
    public static ModuleLayer loadPluginLayer(String moduleName) {
        try {
            ModuleLayer parent = ModuleLayer.boot();
            Path pluginsDir = Path.of("plugins");  // or Paths.get("plugins")

            ModuleFinder finder = ModuleFinder.of(pluginsDir);
            Configuration config = parent.configuration()
                    .resolve(finder, ModuleFinder.of(), Set.of(moduleName));

            ClassLoader scl = ClassLoader.getSystemClassLoader();
            return parent.defineModulesWithOneLoader(config, scl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
