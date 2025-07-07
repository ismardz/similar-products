package com.globant.similarproducts.commons.product;

import com.globant.similarproducts.commons.exceptions.ProductClientException;
import com.globant.similarproducts.commons.product.model.ProductDetail;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductFacade {

    private final ProductClient productClient;

    @Cacheable(value = "getProductDetail", key = "#productId", unless="#result == null")
    public ProductDetail getProductDetail(final String productId) {
        return executeWithExceptionHandling(() -> productClient.getProductDetail(productId));
    }

    @Cacheable(value = "getSimilarProductIds", key = "#productId", unless="#result == null")
    public List<String> getSimilarProductIds(final String productId) {
        return executeWithExceptionHandling(() -> productClient.getSimilarProductIds(productId));
    }

    private <T> T executeWithExceptionHandling(Supplier<T> action) {
        try {
            return action.get();
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new ProductClientException(e);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(cacheNames = {"getProductDetail", "getSimilarProductIds"}, allEntries = true)
    public void evictProductDetailCache() {
        log.info("cacheEvict for getProductDetail and getSimilarProductIds");
    }

}