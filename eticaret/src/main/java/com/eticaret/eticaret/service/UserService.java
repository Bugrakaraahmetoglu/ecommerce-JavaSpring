package com.eticaret.eticaret.service;

import com.eticaret.eticaret.md5.EmailValidator;
import com.eticaret.eticaret.md5.HashUtil;
import com.eticaret.eticaret.model.User;
import com.eticaret.eticaret.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){

        if (!EmailValidator.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Geçersiz e-posta formatı.");
        }

        String hashedPassword = HashUtil.md5(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String hashedPassword = HashUtil.md5(password); // Kullanıcının verdiği şifreyi hash'le
            return hashedPassword.equals(user.getPassword()); // Hash'lenen şifre ile veritabanındaki şifreyi karşılaştır
        }
        return false; // Kullanıcı bulunamazsa false döndür
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User updateUserContactInfo(int userId, String phone, String address) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));
        user.setPhone(phone);
        user.setAddress(address);
        return userRepository.save(user);
    }



}

