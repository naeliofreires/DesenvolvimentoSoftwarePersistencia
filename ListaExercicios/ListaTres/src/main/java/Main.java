public class Main {

    public static void main(String[] args) {

        System.out.println("List 3 - Persistence in files. \n");

//        ConsumingData consumingData = new ConsumingData();
        ReadingXML_SAX readingXML_sax = new ReadingXML_SAX();

//        consumingData.MercadoLivre("xbox");

        readingXML_sax.parseDocument(System.getProperty("user.home") + "/lista-completa-servicos.xml");
        readingXML_sax.saveData("","");

    }
}
