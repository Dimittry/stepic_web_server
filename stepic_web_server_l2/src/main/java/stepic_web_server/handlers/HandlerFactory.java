package stepic_web_server.handlers;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class HandlerFactory {
    private static final String REQUEST_PATH_PROPERTY = "mirror";

    public static IHandler getInstance(String path, Map<String, Object> parameters) {
        String handlerType = defineHandler(path);
        System.err.println("handlerType = " + handlerType);
        if(handlerType.equals(REQUEST_PATH_PROPERTY)) {
            System.err.println("MirrorHandler");
            return new MirrorHandler(parameters);
        } else {
            System.err.println("getDefaultInstance");
            return getDefaultInstance(parameters);
        }
    }

    public static  IHandler getDefaultInstance(Map<String, Object> parameters) {
        return new DefaultHandler(parameters);
    }

    private static String defineHandler(String path) {
        String[] pathParts = path.split("/");
        List<String> parts = Arrays.stream(pathParts).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        return (parts.size() > 0) ? parts.get(0) : "default";
    }
}
