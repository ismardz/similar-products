package com.ismardz.similarproducts.service;

import com.ismardz.similarproducts.commons.mapper.ProductMapper;
import com.ismardz.similarproducts.commons.product.ProductFacade;
import com.ismardz.similarproducts.commons.product.model.ProductDetail;
import com.ismardz.similarproducts.service.model.GetSimilarProductsOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSimilarProductsService {

    private final ProductFacade productFacade;
    private final ProductMapper productMapper;

    public GetSimilarProductsOutput getSimilarProducts(final String productId) {
        return Optional.ofNullable(productId)
            .map(productFacade::getSimilarProductIds)
            .map(this::getSimilarProductsDetailAsync)
            .map(productMapper::toSimilarProductDetail)
            .map(GetSimilarProductsOutput::new)
            .orElseThrow();
    }

    private List<ProductDetail> getSimilarProductsDetailAsync(final List<String> similarProductIds) {
        return similarProductIds.stream()
            .map(this::createCompletableFutureForProductDetail)
            .toList()
            .stream()
            .map(CompletableFuture::join)
            .filter(Objects::nonNull)
            .toList();
    }

    private CompletableFuture<ProductDetail> createCompletableFutureForProductDetail(final String id) {
        return CompletableFuture.supplyAsync(() -> productFacade.getProductDetail(id))
            .exceptionally(ex -> {
                log.info("Error getting detail from pruduct ID: {}. Ignoring this ID for similar products", id, ex);
                return null;
            });
    }
}
