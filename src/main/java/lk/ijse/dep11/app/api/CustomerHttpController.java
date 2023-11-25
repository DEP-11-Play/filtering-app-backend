package lk.ijse.dep11.app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerHttpController {
    @GetMapping
    public String getRespond(){
        return "Hello";
    }
}