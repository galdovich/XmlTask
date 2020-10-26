package main.java.com.galdovich.xml.builder;

import main.java.com.galdovich.xml.builder.impl.StudentsDOMBuilder;
import main.java.com.galdovich.xml.builder.impl.StudentsEventStaxBuilder;
import main.java.com.galdovich.xml.builder.impl.StudentsSAXBuilder;
import main.java.com.galdovich.xml.builder.impl.StudentsStreamStaxBuilder;
import main.java.com.galdovich.xml.exception.ParserException;

public class StudentBuilderFactory {
    private enum TypeParser {
        SAX, DOM, EVENT_STAX, STREAM_STAX
    }

    public StudentBuilderFactory() {
    }

    public static AbstractStudentsBuilder createStudentBuilder(String typeParser) throws ParserException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new StudentsDOMBuilder();
            case SAX:
                return new StudentsSAXBuilder();
            case EVENT_STAX:
                return new StudentsEventStaxBuilder();
            case STREAM_STAX:
                return new StudentsStreamStaxBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
