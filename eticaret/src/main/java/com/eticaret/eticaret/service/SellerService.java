package com.eticaret.eticaret.service;

import com.eticaret.eticaret.md5.EmailValidator;
import com.eticaret.eticaret.md5.HashUtil;
import com.eticaret.eticaret.model.Seller;
import com.eticaret.eticaret.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller saveSeller(Seller saller){

        if (!EmailValidator.isValidEmail(saller.getEmail())) {
            throw new IllegalArgumentException("Geçersiz e-posta formatı.");
        }

        String hashedPassword = HashUtil.md5(saller.getPassword());
        saller.setPassword(hashedPassword);
        return sellerRepository.save(saller);
    }

    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
}
