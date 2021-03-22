package ch.heArc.hotelDiscovery.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.heArc.hotelDiscovery.models.User;
import ch.heArc.hotelDiscovery.repository.IUserRepository;

@Controller

public class UserController {
    
    @Autowired 
    IUserRepository userRepository;
    
    @Autowired 
    
    @GetMapping("/all")
    public  String getAll(Map<String, Object> model) {
        
        model.put("users", userRepository.findAll());
        
        return "liste";
    }
            
    @GetMapping("/form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        
        return "user-form";
    }
        
    @PostMapping("/insert")
    public String insertUser(@ModelAttribute User user, Model model) {
            
        userRepository.save(user);
        
        return "user-form";
        
    }
}
