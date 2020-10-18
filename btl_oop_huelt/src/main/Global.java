package main;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Global {
    public static Stage WINDOWN;

    public static Scene SCENE_LOGIN;
    public static Scene SCENE_HOME;
    public static Scene SCENE_CREATE;
    public static Map<String, String> ALPHAS = new HashMap<String, String>()
    {{
        put("a", "@a"); put("b", "@b"); put("c", "@c"); put("d", "@d");
        put("e", "@e"); put("f", "@f"); put("g", "@g"); put("h", "@h");
        put("i", "@i"); put("j", "@j"); put("k", "@k"); put("l", "@l");
        put("m", "@m"); put("n", "@n"); put("o", "@o"); put("p", "@p");
        put("q", "@q"); put("r", "@r"); put("s", "@s"); put("t", "@t");
        put("u", "@u"); put("v", "@v"); put("w", "@w"); put("x", "@x");
        put("y", "@y"); put("z", "@z");
    }};
}
