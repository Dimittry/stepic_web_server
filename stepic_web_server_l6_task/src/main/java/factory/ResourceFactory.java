package factory;

import sax.ReadXMLFileSAX;

public class ResourceFactory {
    private final static String DIR =  "./";

    public static Object getInstance(String name) {
        return ReadXMLFileSAX.readXML(DIR + name);
    }
}
