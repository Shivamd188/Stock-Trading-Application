package com.stocktrading.Controllers;

import com.stocktrading.model.User;
import com.stocktrading.dto.UserSessionDTO;
import com.stocktrading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered.");
            return "register";
        }
        
        //new usersss
        userRepository.save(user); 
        return "redirect:/login";
    }

   
    @PostMapping("/login")
    public String loginUser(String email, String password, Model model, HttpSession session) {
        User user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }

        // Map User to UserSessionDTO to exclude password
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setId(user.getId());
        userSessionDTO.setEmail(user.getEmail());
        userSessionDTO.setFirstName(user.getFirstName());
        userSessionDTO.setLastName(user.getLastName());

        // Store the DTO in the session
        session.setAttribute("loggedInUser", userSessionDTO);
        return "redirect:/dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear the session
        return "redirect:/login";
    }
}
