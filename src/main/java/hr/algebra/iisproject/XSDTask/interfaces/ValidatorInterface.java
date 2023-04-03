package hr.algebra.iisproject.XSDTask.interfaces;

import org.xml.sax.SAXException;

import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public interface ValidatorInterface {
     Validator initValidator() throws SAXException;
     boolean isValid(File xml) throws SAXException, IOException;
}
