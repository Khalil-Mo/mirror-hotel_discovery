package ch.heArc.hotelDiscovery.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.heArc.hotelDiscovery.models.User;
import ch.heArc.hotelDiscovery.repository.IUserRepository;
import ch.heArc.hotelDiscovery.services.UserService;

@Controller

public class UserController {
    
    @Autowired 
    IUserRepository userRepository;
    
    @Autowired 
    UserService userService;
    
   
    
    /*@GetMapping("/all")
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
        
    }*/
    
    @PostMapping("/signup")
	String signUp(User user) {
    	
    	Optional<User> userExist = userRepository.findByEmail(user.getEmail());
    	if (userExist.isEmpty()) {
    		userService.signUpUser(user);
    		return "redirect:/login";
    	}
    	return "redirect:/signup?exist";
		
	}
    
    @GetMapping("/signup")
	String signUp(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	User u = new User();
        	model.addAttribute("user", u);
    		return "user/signup";
        }
    	
        return "redirect:/";
	}
    
    @GetMapping("/login")
	String login(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    		return "user/login";
        }
    	
        return "redirect:/";
	}
    
}
