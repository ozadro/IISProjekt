package hr.algebra.iisproject.XSDTask.validator;

import net.sf.saxon.Configuration;
import org.springframework.stereotype.Service;
import net.sf.saxon.s9api.*;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;


@Service
public class SoapValidator {

    File xsd;


    {
        try {
            xsd = ResourceUtils.getFile("classpath:footballDataXSD.xsd");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    String soapFile = "SoapFile.xml";

    public String generateXmlFromXQuery(String query) throws SaxonApiException {
    Configuration configuration = new Configuration();
    Processor processor = new Processor(configuration);
    XQueryCompiler xQueryCompiler = processor.newXQueryCompiler();
    XQueryExecutable xQueryExecutable = xQueryCompiler.compile(query);
    XQueryEvaluator xQueryEvaluator = xQueryExecutable.load();
    StringWriter stringWriter = new StringWriter();
    Serializer serializer = processor.newSerializer(stringWriter);
    xQueryEvaluator.run(serializer);

        try(OutputStream outputStream = new FileOutputStream(soapFile)){

            serializer.setOutputStream(outputStream);
            xQueryEvaluator.run(serializer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return stringWriter.toString();

    }

    public boolean validateGeneratedXML(String xml) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(xsd);
        Schema schema = factory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xml)));

        return true;
    }

}
