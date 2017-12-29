package com.yuktamedia.service;

import com.yuktamedia.model.Product;

public interface ProductService {
    public Iterable<Product> listAllProducts();

    public Product getProductById(Integer id);

    public Product saveProduct(Product product);
    
    public Product updateProduct(Product product);
    
    public void deleteProductById(Integer id);
}
