package hr.algebra.iisproject.XSDTask.repository;

import hr.algebra.iisproject.XSDTask.model.Row;
import org.springframework.context.annotation.Bean;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public interface RowRepository {
    List<Row> getAllData();
}
