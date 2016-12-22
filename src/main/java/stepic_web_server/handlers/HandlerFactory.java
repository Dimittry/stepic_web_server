package stepic_web_server.handlers;

import java.util.Map;

public class HandlerFactory {
    private static final String REQUEST_PATH_PROPERTY = "mirror";

    public static IHandler getInstance(String path, Map<String, String[]> parameters) {
        return new MirrorHandler(parameters);
    }
}
