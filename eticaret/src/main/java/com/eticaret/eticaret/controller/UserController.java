package com.eticaret.eticaret.controller;

import com.eticaret.eticaret.model.User;
import com.eticaret.eticaret.repsonse.UpdateUserContactInfoDto;
import com.eticaret.eticaret.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")  // İzin verilen origin (port 5500)
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){

        try{
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }
        catch (Exception e) {
        // Hata durumunda JSON formatında mesaj döndürSystem.out.println("Hata: " + e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginUser, HttpSession session) {
        if (userService.login(loginUser.getEmail(), loginUser.getPassword())) {
            User user = userService.findByEmail(loginUser.getEmail());

            // Kullanıcı bilgilerini session'a ekle
            session.setAttribute("username", user.getName());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("phone", user.getPhone());

            return ResponseEntity.ok(user); // Kullanıcı bilgilerini döndür
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Hatalı girişte null döndür
    }

    // Çıkış yapmak için ek metod
    @GetMapping("/logout")
    public ResponseEntity<String> logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Session'ı temizle
        return ResponseEntity.ok("Çıkış yapıldı"); // Çıkış mesajı döndür
    }

    @PutMapping("/{id}/update-contact-info")
    public ResponseEntity<User> updateContactInfo(@PathVariable int id, @RequestBody UpdateUserContactInfoDto dto) {
        try {
            User updatedUser = userService.updateUserContactInfo(id, dto.getPhone(), dto.getAddress());
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
