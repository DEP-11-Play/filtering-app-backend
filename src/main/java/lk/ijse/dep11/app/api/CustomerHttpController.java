package lk.ijse.dep11.app.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lk.ijse.dep11.app.to.CustomerTO;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CustomerHttpController {
    HikariDataSource pool;
    public CustomerHttpController(Environment env) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        config.setUsername(env.getRequiredProperty("spring.datasource.user"));
        config.setPassword(env.getRequiredProperty("spring.datasource.password"));
        config.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        config.setMaximumPoolSize(env.getRequiredProperty("spring.datasource.hikari-maximum-pool-size", Integer.class));

        pool = new HikariDataSource(config);
    }
    @PreDestroy
    private void destroy(){
        pool.close();
    }

    @GetMapping
    public LinkedList<CustomerTO> getAllCustomers(String q){
        try (Connection connection=pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("" +
                    "SELECT * FROM customer WHERE id LIKE ? OR first_name LIKE ? OR last_name LIKE ? " +
                    "OR contact LIKE ? OR country LIKE ?");
            if (q==null) q="";
            for(int i=1;i<=5;i++) stm.setObject(i,"%"+q+"%");
            ResultSet rst = stm.executeQuery();
            LinkedList<CustomerTO> customerList=new LinkedList<>();
            while (rst.next()){
                int id=rst.getInt("id");
                String firstName = rst.getString("first_name");
                String lastName = rst.getString("last_name");
                String contact = rst.getString("contact");
                String country = rst.getString("country");
                customerList.add(new CustomerTO(id,firstName,lastName,contact,country));
            }
            return customerList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//    @GetMapping
//    public void getAllSortedCustomers(){
//
//    }
//    @GetMapping
//    public void getAllPaginatedCustomers(){
//
//    }
//    @GetMapping
//    public void getAllPaginatedAndSortedCustomers(){
//
//    }
}
