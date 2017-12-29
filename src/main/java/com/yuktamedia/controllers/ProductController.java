/**
 * 
 */
package com.yuktamedia.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.yuktamedia.security.JWTTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuktamedia.model.Product;
import com.yuktamedia.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.log4j.Logger;

/**
 * @author shrikant
 *
 */

@RestController
public class ProductController {
    private ProductService productService;
    
    private static final Logger logger = Logger.getLogger(ProductController.class);
    
    public ProductController() {
		//TODO: Get any initialization steps.
	}

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get the list of products.", nickname = "get_all_products", 
        response = Product.class, responseContainer = "List")
    @RequestMapping(value = "/products", method = {RequestMethod.GET}, produces = "application/json")
    public Iterable<Product> list(HttpSession session) {
        logger.info("Returning all the products.");
        return productService.listAllProducts();
    }
    
    @ApiOperation(value = "Get the product for given id.", nickname = "get_product_by_id", response = Product.class)
    @RequestMapping(value = "product/{product_id}", method = {RequestMethod.GET}, produces = "application/json")
    public Product showProduct(@ApiParam(value="Id of the Product.", required=true)
        @PathVariable (value="product_id") Integer productId, HttpSession session, @RequestHeader(value = "referer", required = false) String referer, @RequestHeader(value="Authorization", required = false) String token) throws IOException {

        String newString = "{\"id\": " + productId.toString() + "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newNode = mapper.readTree(newString);
        boolean isReqFromSwagger = referer.contains("swagger-ui");
        boolean isAuthorized = false;
        if (token != null && token.startsWith("Bearer ")) {
            isAuthorized = JWTTokenCheck.tokenCheck(token, newNode);
        }
        logger.info("Returning the product with id: " + productId);
        if (isAuthorized || isReqFromSwagger)
            return productService.getProductById(productId);
        else
            throw new RuntimeException("Authorization failed");
    }

    @ApiOperation(value = "Create a new product.", nickname = "create_product")
    @RequestMapping(value = "product/new", method = {RequestMethod.POST}, produces = "application/json")
    public Product newProduct(
            @ApiParam(value="JSON Payload containing parameters to create the Product.", required=true)
            @RequestBody JsonNode payload,
            HttpSession session,
            @RequestHeader(value="Authorization", required = false) String token,
            @RequestHeader(value = "referer", required = false) String referer
    ) {
    	
        ObjectMapper mapper = new ObjectMapper();
        Map params = mapper.convertValue(payload, Map.class);

        Integer id = (Integer)params.get("id");
        boolean isReqFromSwagger = referer.contains("swagger-ui");
        boolean isAuthorized = false;
        if (token != null && token.startsWith("Bearer ")) {
            isAuthorized = JWTTokenCheck.tokenCheck(token, payload);
        }
        if (isAuthorized || isReqFromSwagger) {
            Product pn = new Product();
            pn.setId(id);
            pn.setName(params.get("name").toString());
            pn.setColor(params.get("color").toString());
            pn.setImage(params.get("image").toString());

            logger.info("Saving the product with id: " + id);
            return productService.saveProduct(pn);
        } else {
            throw new RuntimeException("Authorization failed");
        }

    }
    
    @ApiOperation(value = "Update an existing product.", nickname = "update_product")
    @RequestMapping(value = "product/update", method = {RequestMethod.PUT}, produces = "application/json")
    public Product updateProduct(@ApiParam(value="JSON Payload containing parameters to edit the Product.", required=true)
        @RequestBody JsonNode payload,
        HttpSession session, @RequestHeader(value="Authorization", required = false) String token, @RequestHeader(value = "referer", required = false) String referer) {
    	
        ObjectMapper mapper = new ObjectMapper();
        Map params = mapper.convertValue(payload, Map.class);

        Integer id = (Integer)params.get("id");
        boolean isReqFromSwagger = referer.contains("swagger-ui");
        boolean isAuthorized = false;
        if (token != null && token.startsWith("Bearer ")) {
            isAuthorized = JWTTokenCheck.tokenCheck(token, payload);
        }
        if (isAuthorized || isReqFromSwagger) {
            Product pn = new Product();
            pn.setId(id);
            pn.setName(params.get("name").toString());
            pn.setColor(params.get("color").toString());
            pn.setImage(params.get("image").toString());

            logger.info("Updating the product with id: " + id);
            return productService.updateProduct(pn);
        } else {
            throw new RuntimeException("Authorization failed");
        }
    }

    @ApiOperation(value = "Delete the product for given id.", nickname = "delete_product_by_id", response = Product.class)
    @RequestMapping(value = "product/{product_id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public void deleteProduct(@ApiParam(value="Id of the Product.", required=true)
        @PathVariable (value="product_id") Integer productId, HttpSession session, @RequestHeader(value="Authorization", required = false) String token, @RequestHeader(value = "referer", required = false) String referer) throws IOException {

        String newString = "{\"id\": " + productId.toString() + "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newNode = mapper.readTree(newString);
        boolean isReqFromSwagger = referer.contains("swagger-ui");
        boolean isAuthorized = false;
        if (token != null && token.startsWith("Bearer ")) {
            isAuthorized = JWTTokenCheck.tokenCheck(token, newNode);
        }
        if (isAuthorized || isReqFromSwagger) {
            logger.info("Deleting the product with id: " + productId);
            productService.deleteProductById(productId);
        } else {
            throw new RuntimeException("Authorization failed");
        }
    }
}
