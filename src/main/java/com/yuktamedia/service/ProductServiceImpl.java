package com.yuktamedia.service;

import com.yuktamedia.dao.ProductMapper;
import com.yuktamedia.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	public ProductMapper productMapper;
	
	public ProductServiceImpl() {

	}
	
	@Override
	public Iterable<Product> listAllProducts() {
		logger.info("Using ProductMapper to return all the products.");
		return productMapper.getAll();
	}

	@Override
	public Product getProductById(Integer id) {
		logger.info("Using ProductMapper to return the product with id: " + id);
		return productMapper.getById(id.intValue());
	}

	@Override
	public Product saveProduct(Product product) {
		logger.info("Using ProductMapper to save the product with id: " + product.getId());
		productMapper.save(product);
		return product;
    }

	@Override
	public Product updateProduct(Product product) {
		logger.info("Using ProductMapper to update the product with id: " + product.getId());
		productMapper.update(product);
		return product;
	}
	
	@Override
	public void deleteProductById(Integer id) {
		logger.info("Using ProductMapper to delete the product with id: " + id);
		productMapper.deleteById(id.intValue());
	}
	
}
