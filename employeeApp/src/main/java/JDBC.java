import java.sql.*;
import javax.xml.bind.annotation.*;
import java.util.*;
import java.io.File;
import javax.xml.bind.*;

public class JDBC {
    public static void Task() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Config.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("src/main/resources/Task.xml");
        Config config = (Config) unmarshaller.unmarshal(xml);
        List<String> list = config.getListOperations();

        /*Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(config, System.out);*/

        try {
            String sql = "INSERT INTO scheme_rights (id, operation_name) values(nextval(content_id_seq), ?)";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:58925/staff", "postgres", "postres");
            PreparedStatement statement = connection.prepareStatement(sql);
            for(String op : list){
                statement.setString(3,op);
            }

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

@XmlRootElement
class Config{
    private List<String> listOperations = new ArrayList<String>();

    @XmlElementWrapper(name="operations")
    @XmlElement(name="operation")
    public List<String> getListOperations() {
        return listOperations;
    }
}
