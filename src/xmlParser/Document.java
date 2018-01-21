package xmlParser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "doc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {
    @XmlElement(name = "field")
    private List<Field> field = null;

    public Document() {
        this.field = new ArrayList<Field>();
    }

    public void setField(List<Field> field) {
        this.field = field;
    }

    public List<Field> getField() {
        return field;
    }

    public void addField(Field field) {
        this.field.add(field);
    }
}