package hr.algebra.iisproject.XSDTask.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class RPCService {

    public String getTemp(String nameOfCity){
        String tempUrl = "https://vrijeme.hr/hrvatska_n.xml";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new URL(tempUrl).openStream());

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("Grad");
            for (int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                String city = element.getElementsByTagName("GradIme").item(0).getTextContent();
                if (city.equalsIgnoreCase(nameOfCity)){
                    return element.getElementsByTagName("Temp").item(0).getTextContent();
                }

            }

        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return "Non existent City";

    }

}
