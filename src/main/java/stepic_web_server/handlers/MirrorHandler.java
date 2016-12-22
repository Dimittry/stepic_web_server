package stepic_web_server.handlers;

import java.util.HashMap;
import java.util.Map;

public class MirrorHandler implements IHandler {
    private static final String REQUEST_PROPERTY = "key";
    private static final String RESULT_PROPERTY = "response";

    private final Map<String, String[]> requestParameters;
    private Map<String, Object> result = new HashMap<>();

    public MirrorHandler(Map<String, String[]>parameters) {
        this.requestParameters = parameters;
        process();
    }

    @Override
    public String getResult() {
        return (String)result.get(RESULT_PROPERTY);
    }

    private void process() {
        String[] values = getParametersValues();

        if(values == null) {
            throw new IllegalStateException("No request parameters.");
        }
        result.put(RESULT_PROPERTY, getFirstValue(values));
    }

    private String[] getParametersValues() {
        return requestParameters.get(REQUEST_PROPERTY);
    }

    private String getFirstValue(String[] values) {
        return (values.length > 0) ? values[0] : "";
    }
}
