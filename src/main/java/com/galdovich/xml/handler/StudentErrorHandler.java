package main.java.com.galdovich.xml.handler;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class StudentErrorHandler extends DefaultHandler {

    private static final String DELIMITER = " : ";
    private static final String HYPHEN = " - ";

    @Override
    public void warning(SAXParseException e) throws SAXException {
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + DELIMITER + e.getColumnNumber();
    }
}
