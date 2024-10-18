package com.eticaret.eticaret.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private Double price;
    private String imageUrl; // Resim URL'si için alan
    private Integer categoryId; // Kategori ID
}
