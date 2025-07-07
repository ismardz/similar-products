package com.ismardz.similarproducts.controller;

import com.ismardz.similarproducts.commons.product.model.ProductDetail;
import com.ismardz.similarproducts.service.GetSimilarProductsService;
import com.ismardz.similarproducts.service.model.SimilarProductDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetSimilarProductsController {

    private final GetSimilarProductsService getSimilarProductsService;

    @Operation(
        operationId = "getProductSimilar",
        summary = "Similar products",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDetail.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Product Not found")
        }
    )
    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<List<SimilarProductDetail>> getSimilarProduct(
        final @PathVariable("productId") String productId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(getSimilarProductsService.getSimilarProducts(productId).similarProducts());
    }

}
