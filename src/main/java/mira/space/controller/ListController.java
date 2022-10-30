package mira.space.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mira.space.configuration.SwaggerConfiguration;
import mira.space.exception.ListNotFoundException;
import mira.space.exception.ProductNotFoundException;
import mira.space.model.List;
import mira.space.model.Product;
import mira.space.model.repository.ListRepository;
import mira.space.model.repository.ProductRepository;
import mira.space.service.ListService;
import mira.space.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/lists")
@Api(tags = {SwaggerConfiguration.LIST_CONTROLLER_TAG})
public class ListController {

    @Autowired
    ListService listService;
    @Autowired
    ListRepository listRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    @ApiOperation("Get all list entities with products kcal in sum for each")
    public ResponseEntity<?> getLists() {
        HashMap<String, List> kcalRatio = listService.getKcalRatio();
        if (kcalRatio == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(kcalRatio);
    }

    @PostMapping
    @ApiOperation("Save list to DataBase")
    public ResponseEntity<List> create(@RequestBody List list) {
        List newList = listService.create(list);
        if (newList == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(listRepository.save(newList));
    }

    @PutMapping("/{id}")
    @ApiOperation("Add product to existing list by id")
    public ResponseEntity<List> addProductToList(@PathVariable Long id, @RequestBody Product product) {
        List updatedList = listRepository.findById(id).orElseThrow(() -> new ListNotFoundException(1L));
        Product newProduct = productService.create(product);
        if (newProduct == null || newProduct.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        newProduct = productRepository.findById(newProduct.getId()).orElseThrow(ProductNotFoundException::new);
        updatedList.getProducts().add(newProduct);
        newProduct.setList(updatedList);
        return ResponseEntity.ok(listRepository.save(updatedList));
    }
}
