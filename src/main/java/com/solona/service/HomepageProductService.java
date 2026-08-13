package com.solona.service;

import com.solona.dto.ProductSectionConfig;
import com.solona.modal.Product;

import java.util.List;

public interface HomepageProductService {

    List<Product> getProducts(ProductSectionConfig config);

}
