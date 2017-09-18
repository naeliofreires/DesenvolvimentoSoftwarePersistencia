package validator_xml;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import modelo.Elemento;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class getPathXSD extends DefaultHandler {

    private String tempVal;
    private Elemento tempElemento;
    private ArrayList<String> myEmpls = new ArrayList<>();

    public getPathXSD(){ }

    public void parseDocument(String path){


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(path, this);

        }catch(SAXException	se)	{
            se.printStackTrace();
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }catch	(IOException ie){
            ie.printStackTrace();
        }

    }

//    API callback methods

    public void startDocument() {
        System.out.println("\nIniting the Parsing...\n");
    }

    public void startElement(String	uri, String localName, String qName, Attributes attributes)	throws SAXException	{

        for(int i = 0; i < attributes.getLength(); i++)
            if(attributes.getQName(i).equalsIgnoreCase("xsi:schemaLocation"))
                myEmpls.add(attributes.getValue(i));
    }

    public void characters(char[]	ch,	int	start,	int length)	throws SAXException	{
        tempVal	=	new	String(ch,start,length);
    }

    public void endElement(String uri, String localName, String tag) throws SAXException {
        System.out.println("\nIniting the Parsing...\n");
    }

    public String getPath(String xml){
        parseDocument(xml);
        return myEmpls.get(0);
    }
}
