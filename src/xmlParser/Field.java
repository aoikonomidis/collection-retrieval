package xmlParser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
public class Field {
    @XmlAttribute(name = "name")
    private String name;
    @XmlValue
    private String value;

    private static final String[] FIELDNAMES = {
        "id",
        "title",
        "date",
        "author",
        "entry_date",
        "references",
        "abstract",
        "content",
        "keywords"
    };

    public Field() {}

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        switch (name) {
            case "I":
                this.name = FIELDNAMES[0];
                break;
            case "T":
                this.name = FIELDNAMES[1];
                break;
            case "B":
                this.name = FIELDNAMES[2];
                break;
            case "A":
                this.name = FIELDNAMES[3];
                break;
            case "N":
                this.name = FIELDNAMES[4];
                break;
            case "X":
                this.name = FIELDNAMES[5];
                break;
            case "W":
                this.name = FIELDNAMES[6];
                break;
            case "C":
                this.name = FIELDNAMES[7];
                break;
            case "K":
                this.name = FIELDNAMES[8];
                break;
            default: 
                System.out.println("Invalid field name");
                break;
        }
        // this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}