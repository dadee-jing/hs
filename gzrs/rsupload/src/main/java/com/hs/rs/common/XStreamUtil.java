package com.hs.rs.common;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

/**
 * Created on 16-9-27.
 *
 * @author Shepherd
 * @version 1.0
 */
public abstract class XStreamUtil {

    public static String objectToXml(Object object) {
        XmlFriendlyNameCoder replacer = new XmlFriendlyNameCoder("__", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new Xpp3Driver(replacer);
        XStream xstream = new XStream(hierarchicalStreamDriver);
        xstream.processAnnotations(object.getClass());
        xstream.ignoreUnknownElements();
        return xstream.toXML(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(String xml, Class<T> objectClass) {
        XmlFriendlyNameCoder replacer = new XmlFriendlyNameCoder("__", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new Xpp3Driver(replacer);
        XStream xstream = new XStream(hierarchicalStreamDriver);
        xstream.processAnnotations(objectClass);
        xstream.ignoreUnknownElements();
        return (T) xstream.fromXML(xml);
    }

    public static String xmlEnscape(String source) {
        String result = source.replaceAll("<", "&lt;");
        result = result.replaceAll(">", "&gt;");
        return result;
    }

    public static String xmlDenscape(String source) {
        String result = source.replaceAll("&lt;", "<");
        result = result.replaceAll("&gt;", ">");
        result = result.replaceAll("&amp;", "&");
        return result;
    }

}
