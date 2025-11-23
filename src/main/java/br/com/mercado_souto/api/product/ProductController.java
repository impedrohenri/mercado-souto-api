package br.com.mercado_souto.api.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercado_souto.model.category.CategoryService;
import br.com.mercado_souto.model.product.Product;
import br.com.mercado_souto.model.product.ProductService;
import br.com.mercado_souto.model.seller.SellerService;
@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/{idSeller}")
    public ResponseEntity<Product> create(@PathVariable Long idSeller,@RequestBody ProductRequest request) {

        Product newProduct = request.build();
        newProduct.setSeller(sellerService.findById(idSeller));
        newProduct.setCategory(categoryService.findById(request.getIdCategory()));
        Product product = productService.create(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = productService.findAll();
        return ResponseEntity.status( HttpStatus.OK).body(list);
    }

    @GetMapping("/by-seller/{idSeller}")
    public ResponseEntity<List<Product>> findBySeller(@PathVariable Long idSeller){

        sellerService.findById(idSeller);//garantir q o seller existe
        List<Product> list = productService.findBySeller(idSeller);

        return ResponseEntity.status( HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status( HttpStatus.OK).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductRequest request) {

        Product modifiedProduct = request.build();
        modifiedProduct.setCategory(categoryService.findById(request.getIdCategory()));
        Product product = productService.update(id, modifiedProduct);

        return ResponseEntity.status( HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status( HttpStatus.NO_CONTENT).build();
    }
}
