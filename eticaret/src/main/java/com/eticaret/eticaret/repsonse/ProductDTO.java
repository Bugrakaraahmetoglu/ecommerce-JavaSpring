package com.eticaret.eticaret.repsonse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private double price;
    private String imageUrl;
    private String categoryName;
}