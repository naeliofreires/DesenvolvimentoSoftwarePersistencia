import jsoup.ConsumingData;
import validator_xml.XSDValidator;
import validator_xml.getPathXSD;
import xml_sax.ReadingXML_SAX;

import static java.lang.System.getProperty;

public class Main {

    public static void main(String[] args) {

//        System.out.println("List 3 - Persistence in files. \n");
//
//        ConsumingData consumingData = new ConsumingData();
//        consumingData.MercadoLivre("xbox");
//
//        ReadingXML_SAX readingXML_sax = new ReadingXML_SAX();
//        readingXML_sax.parseDocument(getProperty("user.home") + "/lista-completa-servicos.xml");
//        readingXML_sax.saveData();

        String xml = System.getProperty("user.home") + "/students.xml";
        XSDValidator xsd = new XSDValidator();
        boolean rsp = xsd.validateXMLSchema(xml);

        System.out.println(rsp);

//        getPathXSD gpx = new getPathXSD();
//        System.out.println(gpx.getPath(System.getProperty("user.home") + "/students.xml"));

    }
}
