package main.java.com.galdovich.xml.builder.impl;

import main.java.com.galdovich.xml.builder.AbstractStudentsBuilder;
import main.java.com.galdovich.xml.entity.Student;
import main.java.com.galdovich.xml.exception.ParserException;
import main.java.com.galdovich.xml.handler.StudentErrorHandler;
import main.java.com.galdovich.xml.handler.StudentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class StudentsSAXBuilder extends AbstractStudentsBuilder {

    private StudentHandler studentHandler;
    private StudentErrorHandler studentErrorHandler;
    private XMLReader reader;

    public StudentsSAXBuilder() throws ParserException {
        init();
    }

    public StudentsSAXBuilder(Set<Student> students) throws ParserException {
        super(students);
        init();
    }

    private void init() throws ParserException {
        studentHandler = new StudentHandler();
        studentErrorHandler = new StudentErrorHandler();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setContentHandler(studentHandler);
            reader.setErrorHandler(studentErrorHandler);
        } catch (SAXException | ParserConfigurationException e) {
            throw new ParserException("Problem with creating SAX parser", e);
        }
    }

    @Override
    public void buildSetStudents(String fileName) throws ParserException {
        try {
            reader.parse(fileName);
            students = studentHandler.getStudents();
        } catch (IOException | SAXException e) {
            throw new ParserException("Problem with parsing XML", e);
        }
    }
}
