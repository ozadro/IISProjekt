package hr.algebra.iisproject.XSDTask.validator;


import hr.algebra.iisproject.XSDTask.interfaces.ValidatorInterface;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class XsdValidator implements ValidatorInterface {
    File xsd;


    {
        try {
            xsd = ResourceUtils.getFile("classpath:footballDataXSD.xsd");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Validator initValidator() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(xsd);
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }

    @Override
    public boolean isValid(File xml) throws SAXException, IOException {
        Validator validator = initValidator();
        try {
            validator.validate(new StreamSource(xml));
            return true;
        } catch (SAXException e) {
            return false;
        }

    }

}
