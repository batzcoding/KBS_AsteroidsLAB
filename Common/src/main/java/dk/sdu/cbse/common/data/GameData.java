package dk.sdu.cbse.common.data;

import java.util.HashMap;
import java.util.Map;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final Map<String, Object> displayData = new HashMap<>();

    public Map<String, Object> getDisplayData() {
        return displayData;
    }
    private final GameKeys keys = new GameKeys();


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


}
