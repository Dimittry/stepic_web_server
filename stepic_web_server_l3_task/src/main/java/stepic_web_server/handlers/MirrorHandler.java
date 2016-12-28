package stepic_web_server.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class MirrorHandler implements IHandler {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String PARAMETERS_PROPERTY = "parameters";
    private static final String REQUEST_PARAMETER_PROPERTY = "key";
    private static final String RESULT_PROPERTY = "response";
    private static final String TEMPLATE = "mirror.html";

    private final Map<String, Object> pageVariables;
    private Map<String, Object> variables = new HashMap<>();

    public MirrorHandler(Map<String, Object> pageVariables) {
        this.pageVariables = pageVariables;
        process();
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
        for(Map.Entry<String, Object> var : pageVariables.entrySet()) {
            variables.put(var.getKey(), var.getValue());
        }
        return variables;
    }

    private void process() {
        String[] values = getParametersValues();

        if(values == null) {
            throw new IllegalStateException("No request parameters.");
        }
        variables.put(RESULT_PROPERTY, getFirstValue(values));
    }

    private String[] getParametersValues() {
        Map<String, String[]> parameters = (Map<String, String[]>)pageVariables.get(PARAMETERS_PROPERTY);
        if(parameters == null) return EMPTY_STRING_ARRAY;
        String[] parametersValues = parameters.get(REQUEST_PARAMETER_PROPERTY);
        return (parametersValues == null) ? EMPTY_STRING_ARRAY : parametersValues;
    }

    private String getFirstValue(String[] values) {
        return (values.length > 0) ? values[0] : "";
    }
}
