package hr.algebra.iisproject.XSDTask.repository;

import hr.algebra.iisproject.XSDTask.model.Root;
import hr.algebra.iisproject.XSDTask.model.Row;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class RowRepositoryImpl implements RowRepository{
    File file;

    {
        try {
             file = ResourceUtils.getFile("classpath:footballData.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static List<Row> row = new ArrayList<>();


    @Override
    public List<Row> getAllData()  {
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Root root = (Root) unmarshaller.unmarshal(file);

            row = root.getRows();

            } catch (JAXBException ex ) {
            ex.printStackTrace();
        }

        return row;
    }


}
