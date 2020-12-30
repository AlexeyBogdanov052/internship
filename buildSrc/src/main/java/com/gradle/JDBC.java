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
        List<Operation> list = operations.getList();

        try {
            String sql = "INSERT INTO operations (id, operation_name) values(nextval('content_id_seq'::regclass), ?)";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/staff", "postgres", "postgres");
            PreparedStatement statement = connection.prepareStatement(sql);
            for(Operation op : list){
                statement.setString(1, op.getName());
                statement.executeUpdate();
            }
            //int rows = statement.executeUpdate();
            //System.out.printf("%d rows added", rows);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

@XmlRootElement(name = "operations")
class Operations{
    @XmlElement(name="operation")
    private List<Operation> operations = new ArrayList<Operation>();

    public List<Operation> getList() {
        return operations;
    }

    public void setList(List<Operation> operations) {
        this.operations = operations;
    }
}

@XmlRootElement(name = "operation")
class Operation{
    private String name;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
