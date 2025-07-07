package com.globant.similarproducts.commons.product;

import com.globant.similarproducts.commons.product.model.ProductDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "productClient", url = "${client.product.url}")
public interface ProductClient {

    @GetMapping("${client.product.endpoints.similar-ids}")
    List<String> getSimilarProductIds(@PathVariable String productId);

    @GetMapping("${client.product.endpoints.product-detail}")
    ProductDetail getProductDetail(@PathVariable String productId);
}