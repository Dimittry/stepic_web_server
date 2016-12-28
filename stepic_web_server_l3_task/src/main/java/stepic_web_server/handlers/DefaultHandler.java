package stepic_web_server.handlers;

import java.util.HashMap;
import java.util.Map;

public class DefaultHandler implements IHandler {
    private static final String TEMPLATE = "page.html";

    private Map<String, Object> pageVariables;

    public DefaultHandler(Map<String, Object> pageVariables) {
        this.pageVariables = pageVariables;
    }

    @Override
    public String getResult() {
        return "";
    }

    @Override
    public String getTemplateFile() {
        return TEMPLATE;
    }

    @Override
    public Map<String, Object> getVariables() {
        String param = "parameters";
        Object paramValue = pageVariables.get(param);
        pageVariables.replace(param, paramValue, paramValue.toString());
        return pageVariables;
    }
}
