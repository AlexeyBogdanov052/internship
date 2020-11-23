package com.gradle;

import com.sun.xml.bind.v2.runtime.property.Property;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.io.FileReader;
import java.sql.*;
import javax.xml.bind.annotation.*;
import java.util.*;
import java.io.File;
import javax.xml.bind.*;

public class JDBC extends DefaultTask {

    @TaskAction
    void Task() throws JAXBException, IOException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        JAXBContext jc = JAXBContext.newInstance(Operations.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("employeeApp/src/main/resources/Task.xml");
        Operations operations = (Operations) unmarshaller.unmarshal(xml);
        //System.out.println(operations);
        List<String> list = operations.getList();

        try {
            String sql = "INSERT INTO scheme_rights (id, operation_name) values(nextval(content_id_seq), ?)";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/staff", "postgres", "postgres");
            PreparedStatement statement = connection.prepareStatement(sql);
            /*for(String op : list){
                System.out.println(op);
            }*/
            //statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

@XmlRootElement(name = "operations")
class Operations{
    private List<String> operations = new ArrayList<String>();

    @XmlElement(name="operation")
    public List<String> getList() {
        return operations;
    }
}
