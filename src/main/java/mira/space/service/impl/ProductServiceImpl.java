package mira.space.service.impl;

import mira.space.model.Product;
import mira.space.service.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product create(Product product) {
        if (product != null
                && product.getId() != null
                && Strings.isNotBlank(product.getName())
                && product.getKcal() >= 0) {
            Product newProduct = new Product();
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            String description = product.getDescription() == null ? "" : product.getDescription();
            newProduct.setDescription(description);
            newProduct.setKcal(product.getKcal());
            newProduct.setList(product.getList());
            return newProduct;
        }
        return null;
    }
}
