import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.io.*;
import xmlParser.*;

public class XmlParser {
    private static final String FILENAME = "./../data/cacm/cacm.all";

    public static void main(String[] args) throws JAXBException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String sCurrentLine, keywords, title;
            String[] splt = null;
            String[] splt_value = null;
            Documents documents = new Documents();
            Document doc = null;
            Field field = null;
            ArrayList<String> list = null;
            int count = 0;

            sCurrentLine = br.readLine();
            while (sCurrentLine != null) {
                // new doc
                if (sCurrentLine.startsWith(".I")) {
                    doc = new Document();
                    field = new Field();
                    splt = sCurrentLine.split("");
                    field.setName(splt[1]);
                    splt_value = sCurrentLine.split(" ");
                    field.setValue(splt_value[splt_value.length - 1]);
                    doc.addField(field);
                }

                sCurrentLine = br.readLine();
                while ((sCurrentLine != null) && !sCurrentLine.startsWith(".I")) {
                    // if title in multiple lines concatenate in one field
                    if (sCurrentLine.startsWith(".T")) {
                        splt = sCurrentLine.split("");
                        field = new Field();
                        field.setName(splt[1]);
                        list = new ArrayList<String>();

                        sCurrentLine = br.readLine();
                        while ((sCurrentLine != null) && !sCurrentLine.startsWith(".")) {
                            if (!sCurrentLine.endsWith(" ")) {
                                sCurrentLine = sCurrentLine.concat(" ");
                            }
                            list.add(sCurrentLine);
                            sCurrentLine = br.readLine();
                        }
                        
                        title = String.join("", list);
                        field.setValue(title.replaceAll("\\s+", " "));
                        doc.addField(field);
                    }

                    // if keywords or abstract concatenate all in one field
                    if (sCurrentLine.startsWith(".K") || sCurrentLine.startsWith(".W")) {
                        splt = sCurrentLine.split("");
                        field = new Field();
                        field.setName(splt[1]);
                        list = new ArrayList<String>();

                        sCurrentLine = br.readLine();
                        while ((sCurrentLine != null) && !sCurrentLine.startsWith(".")) {
                            if (!sCurrentLine.endsWith(" ")) {
                                sCurrentLine = sCurrentLine.concat(" ");
                            }
                            list.add(sCurrentLine);
                            sCurrentLine = br.readLine();
                        }
                        
                        keywords = String.join("", list);
                        field.setValue(keywords.replaceAll("\\s+", " "));
                        doc.addField(field);
                    } 
                    
                    // new field
                    if (sCurrentLine.startsWith(".")) {
                        splt = sCurrentLine.split("");
                        field = new Field();
                        field.setName(splt[1]);
                        sCurrentLine = br.readLine();
                        field.setValue(sCurrentLine.replaceAll("\\s+", " "));
                        doc.addField(field);
                    } 
                    
                    sCurrentLine = br.readLine();
                    while ((sCurrentLine != null) && !sCurrentLine.startsWith(".")) {
                        field = new Field();
                        field.setName(splt[1]);
                        field.setValue(sCurrentLine.replaceAll("\\s+", " "));
                        doc.addField(field);
                        sCurrentLine = br.readLine();
                    }
                }
                documents.addDocument(doc);
                count++;
            }
            
            System.out.println(count + " docs added.");

            JAXBContext jaxbContext = JAXBContext.newInstance(Documents.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the document list in xml file
            jaxbMarshaller.marshal(documents, new File("./../data/results/cacm.all.xml"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}