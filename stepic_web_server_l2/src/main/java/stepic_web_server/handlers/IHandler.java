package stepic_web_server.handlers;

import java.util.Map;

public interface IHandler {
    String getResult();

    String getTemplateFile();

    Map<String, Object> getVariables();

}
