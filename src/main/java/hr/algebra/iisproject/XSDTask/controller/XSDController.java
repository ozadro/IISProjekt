package hr.algebra.iisproject.XSDTask.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import hr.algebra.iisproject.XSDTask.model.Row;
import hr.algebra.iisproject.XSDTask.repository.RowRepositoryImpl;
import hr.algebra.iisproject.XSDTask.service.RPCService;
import hr.algebra.iisproject.XSDTask.validator.RNGValidator;
import hr.algebra.iisproject.XSDTask.validator.SoapValidator;
import hr.algebra.iisproject.XSDTask.validator.XsdValidator;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfig;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarFile;


@Controller
@RequestMapping("/xsd")
public class XSDController {

//    private RowRepositoryImpl rowRepositoryImpl;
//
//    public XSDController(RowRepositoryImpl rowRepositoryImpl) {
//        this.rowRepositoryImpl = rowRepositoryImpl;
//    }

   private String soapFile = "SoapFile.xml";
   private File xml;

    {
        try {
            xml = ResourceUtils.getFile("classpath:footballData.xml");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    RPCService rpcService = new RPCService();
    SoapValidator soapValidator = new SoapValidator();
    XsdValidator xsdValidator = new XsdValidator();
    RNGValidator rngValidator = new RNGValidator();

   @GetMapping("/xsdValidate.html")
    public String openXMLPage(){
       return "xsdValidate";
    }

   @PostMapping("/validateXML.html")
    public String validateXML(Model model) {
       String ok = "Valid";
       String notOk ="invalid";
       try {
           if (xsdValidator.isValid(xml)){

               model.addAttribute("XSDmessage", ok);
           }
           else {

               model.addAttribute("XSDmessage", notOk);
           }
       } catch (SAXException | IOException e) {
           throw new RuntimeException(e);
       }

       return "xsdValidate";
    }

    @PostMapping("/validateRNG.html")
    public String validateRNG(Model model) {
        String ok = "Valid";
        String notOk ="invalid";
        try {

            if (rngValidator.isValid(xml)){
                model.addAttribute("RNGmessage", ok);
            }
            else {
                model.addAttribute("RNGmessage", notOk);
            }
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return "xsdValidate";

    }

    @GetMapping("/validateDHMZ.html")
    public String getDHMZ() throws XmlRpcException {
        WebServer webServer = new WebServer(8081);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        PropertyHandlerMapping propertyHandler = new PropertyHandlerMapping();
        propertyHandler.addHandler("RPCService", RPCService.class);
        xmlRpcServer.setHandlerMapping(propertyHandler);

        return "validateDHMZ";

    }

    @PostMapping("/validateDHMZ.html")
    public String validateDHMZ(@RequestParam("city") String city, Model model){
        if (rpcService.getTemp(city).equals("Non existent City")){
            model.addAttribute("Error", "Non existent City");
        } else{
            model.addAttribute("Temp", "Temperature of "+ city + " is: " + rpcService.getTemp(city) + " Celsius");
        }
        return "validateDHMZ";
    }


    @PostMapping("/validateSOAP.html")
    public String validateSoap(@RequestParam("xQuery") String xQuery, Model model){
        try{
            model.addAttribute("Result",soapValidator.generateXmlFromXQuery(xQuery));
            soapValidator.validateGeneratedXML(soapFile);
            model.addAttribute("Validated","Valid");
            return "xsdValidate";
        }
        catch (Exception e) {
            model.addAttribute("Invalid","Invalid");
            return "xsdValidate";
        }

    }




}
