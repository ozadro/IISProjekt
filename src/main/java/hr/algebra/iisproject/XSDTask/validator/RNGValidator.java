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
public class RNGValidator implements ValidatorInterface {
    File rng;

    {
        try {
            rng = ResourceUtils.getFile("classpath:footballDataRNG.rng");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Validator initValidator() throws SAXException {
        System.setProperty(SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI, "com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory");
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);
        Source schemaFile = new StreamSource(rng);
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
