package com.eticaret.eticaret.controller;


import com.eticaret.eticaret.model.Seller;
import com.eticaret.eticaret.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")  // İzin verilen origin (port 5500)
    @PostMapping("/register")
    public ResponseEntity<Seller> registerUser(@RequestBody Seller seller){

        try{
            Seller saveSaller = sellerService.saveSeller(seller);
            return ResponseEntity.ok(saveSaller);
        }
        catch (Exception e) {
            // Hata durumunda JSON formatında mesaj döndürSystem.out.println("Hata: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public List<Seller> getAllSellers(){
        return sellerService.getAllSellers();
    }
}
