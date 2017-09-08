import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class ReadingXML_SAX extends DefaultHandler {

    private CheckingFile checkingFile;
    private String tempVal;
    private Elemento tempElemento;
    private ArrayList<Elemento> myEmpls = new ArrayList<>();

    ReadingXML_SAX(){
        this.checkingFile = new CheckingFile();
    }

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

        if(qName.equalsIgnoreCase("servico")) {
            tempElemento = new Elemento();
        }

    }

    public void characters(char[]	ch,	int	start,	int length)	throws SAXException	{
        tempVal	=	new	String(ch,start,length);
    }

    public void endElement(String uri, String localName, String tag) throws SAXException {

        if (tag.equalsIgnoreCase("servico")) {
            myEmpls.add(tempElemento);
        } else if (tag.equalsIgnoreCase("nome")) {
            tempElemento.setNome(tempVal.toString());
        } else if (tag.equalsIgnoreCase("dbId")) {
            tempElemento.setId(tempVal.toString());
        }
        tempVal = null;
    }

    public void saveData(String id, String name){
        try {
            // verifying that the file exists, if not, it is created
            if(!this.checkingFile.fileExists())
                checkingFile.createFile();

            checkingFile.getProperties().load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));

            OutputStream os	=	new	FileOutputStream(checkingFile.getProperties().getProperty("file-path"),true);
            OutputStreamWriter osw	=	new	OutputStreamWriter(os);
            BufferedWriter bw	=	new	BufferedWriter(osw);

            for (Elemento x : this.myEmpls) {
                bw.write(x.getId() + "," + x.getNome());
                bw.newLine();
            }

            bw.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}//final class


