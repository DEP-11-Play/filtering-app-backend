package lk.ijse.dep11.app.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.env.Environment;
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

public class CustomerHttpController {
    HikariDataSource pool;
    public CustomerHttpController(Environment env) {

        HikariConfig config=new HikariConfig();
        config.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        config.setUsername(env.getRequiredProperty("spring.datasource.user"));
        config.setPassword(env.getRequiredProperty("spring.datasource.password"));
        config.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        config.setMaximumPoolSize(env.getRequiredProperty("spring.datasource.hikari-maximum-size",Integer.class));

        pool = new HikariDataSource(config);
    }
    @PreDestroy
    private void destroy(){
        pool.close();
    }

    @GetMapping
    public void getAllCustomers(String q){
      

    }
    @GetMapping
    public void getAllSortedCustomers(){

    }
    @GetMapping
    public void getAllPaginatedCustomers(){

    }
    @GetMapping
    public void getAllPaginatedAndSortedCustomers(){

    }
}
