import jsoup.ConsumingData;
import validator_xml.XSDValidator;
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
        boolean rsp = XSDValidator.validateXMLSchema(xml);

        System.out.println(rsp);

    }
}
