package xmlParser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "add")
@XmlAccessorType (XmlAccessType.FIELD)
public class Documents {
    @XmlElement(name = "doc")
    private List<Document> documents = null;

    public Documents(List<Document> documents) {
        this.documents = documents;
    }

    public Documents() {
        this.documents = new ArrayList<Document>();
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document doc) {
        this.documents.add(doc);
    }
}
