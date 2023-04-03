package hr.algebra.iisproject.XSDTask.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
    public class Root {

        @XmlElement(name = "row")
        private List<Row> row;

        public Root(List<Row> row) {
            this.row = row;
        }

        public List<Row> getRows() {
            return row;
        }

        public void setRows(List<Row> row) {
            this.row = row;
        }
}
