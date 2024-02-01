package com.tuto.api.controllers.admin.product;

import com.tuto.api.entities.BrandEntity;
import com.tuto.api.entities.ProductEntity;
import com.tuto.api.services.BrandService;
import com.tuto.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.lang.String;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;
    private final BrandService brandService;

    private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    @Autowired
    public AdminProductController(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }

    @GetMapping
    public String adminProductPage(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "admin/product/product";
    }

    @GetMapping("/{uuid}")
    public String adminProductDetailsPage(@PathVariable UUID uuid, Model model) {
        Optional<ProductEntity> optionalProduct = productService.getByUuid(uuid);

        if (optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
            model.addAttribute("product", product);

            return "admin/product/productDetails";
        } else {
            return "redirect:/admin/products";
        }
    }

    @GetMapping("/{uuid}/edit")
    public String adminProductEditPage(@PathVariable UUID uuid, Model model) {
        Optional<ProductEntity> optionalProduct = productService.getByUuid(uuid);
        List<BrandEntity> brands = brandService.getAllBrands();

        if (optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("brands", brands);

            return "admin/product/productEdit";
        } else {
            return "redirect:/admin/products";
        }
    }

    @PostMapping("/{uuid}/edit")
    public String adminProductEdit(
            @PathVariable UUID uuid,
            @ModelAttribute ProductEntity partialProduct
    ) {
        productService.patchProduct(uuid, partialProduct);

        return "redirect:/admin/products";
    }


    @GetMapping("/{uuid}/delete")
    public String adminProductDelete(@PathVariable UUID uuid) {
        productService.deleteProduct(uuid);

        return "redirect:/admin/products";
    }
}
