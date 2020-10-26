package main.java.com.galdovich.xml;

import main.java.com.galdovich.xml.builder.AbstractStudentsBuilder;
import main.java.com.galdovich.xml.builder.StudentBuilderFactory;
import main.java.com.galdovich.xml.exception.ParserException;

public class Main {

    private final static String DOM_TYPE = "dom";
    private final static String SAX_TYPE = "sax";
    private final static String EVENT_STAX_TYPE = "event_stax";
    private final static String STREAM_STAX_TYPE = "stream_stax";
    private final static String PATH_TO_FILE = "target/classes/data/student.xml";

    public static void main(String[] args) throws ParserException {

        StudentBuilderFactory sFactory = new StudentBuilderFactory();

        //DOM_BUILDER
        AbstractStudentsBuilder builderDOM = sFactory.createStudentBuilder(DOM_TYPE);
        builderDOM.buildSetStudents(PATH_TO_FILE);
        System.out.println(builderDOM.getStudents());
        //SAX_BUILDER
        AbstractStudentsBuilder builderSAX = sFactory.createStudentBuilder(SAX_TYPE);
        builderSAX.buildSetStudents(PATH_TO_FILE);
        System.out.println(builderSAX.getStudents());
        //EVENT_STAX BUILDER
        AbstractStudentsBuilder builderEvenStax = sFactory.createStudentBuilder(EVENT_STAX_TYPE);
        builderEvenStax.buildSetStudents(PATH_TO_FILE);
        System.out.println(builderEvenStax.getStudents());
        //STREAM_STAX BUILDER
        AbstractStudentsBuilder builderStreamStax = sFactory.createStudentBuilder(STREAM_STAX_TYPE);
        builderStreamStax.buildSetStudents(PATH_TO_FILE);
        System.out.println(builderStreamStax.getStudents());
    }
}
