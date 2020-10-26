package main.java.com.galdovich.xml.handler;

import main.java.com.galdovich.xml.entity.Student;
import main.java.com.galdovich.xml.entity.StudentEnum;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


public class StudentHandler extends DefaultHandler {

    private static final String DEFAULT_FACULTY = "mmf";
    private Set<Student> students;
    private Student current;
    private StudentEnum currentXmlTag;
    private EnumSet<StudentEnum> withText;

    public StudentHandler() {
        students = new HashSet<>();
        withText = EnumSet.range(StudentEnum.NAME, StudentEnum.STREET);
    }

    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (StudentEnum.STUDENT.getValue().equals(qName)) {
            current = new Student();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getLength() == 1) {
                    if (attributes.getLocalName(i).equals(StudentEnum.LOGIN.getValue())) {
                        current.setLogin(attributes.getValue(i));
                    }
                    current.setFaculty(DEFAULT_FACULTY);
                } else {
                    if (attributes.getLocalName(i).equals(StudentEnum.LOGIN.getValue())) {
                        current.setLogin(attributes.getValue(i));
                    }
                    if (attributes.getLocalName(i).equals(StudentEnum.FACULTY.getValue())) {
                        current.setFaculty(attributes.getValue(i));
                    }
                }
            }
        } else {
            StudentEnum temp = StudentEnum.valueOf(qName.toUpperCase());
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (StudentEnum.STUDENT.getValue().equals(qName)) {
            students.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME:
                    current.setName(data);
                    break;
                case TELEPHONE:
                    current.setTelephone(Long.parseLong(data));
                    break;
                case STREET:
                    current.getAddress().setStreet(data);
                    break;
                case CITY:
                    current.getAddress().setCity(data);
                    break;
                case COUNTRY:
                    current.getAddress().setCountry(data);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
