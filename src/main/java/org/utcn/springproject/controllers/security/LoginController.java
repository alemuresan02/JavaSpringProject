package org.utcn.springproject.controllers.security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utcn.springproject.data.UserRepository;
import org.utcn.springproject.models.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Controller
@RequestMapping("home/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "LogIn");
        model.addAttribute(new User());
        return "security/login";
    }

    @PostMapping
    public String processLoginForm(@ModelAttribute @Valid User newUser,
                                   Errors errors,
                                   Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "security/login";
        }

        String hashedPassw = hashPassword(newUser.getPassword());

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(newUser.getUsername(), hashedPassw);
        if (userOptional.isPresent()) {

            int role = userOptional.get().getRole();

            if (role == 1) {
                return "redirect:/home/users";
            } else if (role == 2) {
                return "redirect:/home/books";
            } else {
                return "redirect:/home/customer";
            }
        }

        System.out.println("Autentificare eșuată!");
        model.addAttribute("title", "Login");
        model.addAttribute("userDoesNotExistError", "User does not exist!");
        return "security/login";
    }

    public String hashPassword(String password) {
        try {
            // Sercured Hash Algorithm - 256
            // 1 byte = 8 biți
            // 1 byte = 1 char
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
