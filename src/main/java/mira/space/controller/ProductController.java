package mira.space.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mira.space.configuration.SwaggerConfiguration;
import mira.space.model.Product;
import mira.space.model.repository.ProductRepository;
import mira.space.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Api(tags = {SwaggerConfiguration.PRODUCT_CONTROLLER_TAG})
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    @ApiOperation("Get all product entities")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping
    @ApiOperation("Save product in DataBase")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product newProduct = productService.create(product);
        if (newProduct == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productRepository.save(newProduct));
    }
}
