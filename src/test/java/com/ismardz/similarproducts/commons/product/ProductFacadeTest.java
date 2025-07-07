package com.ismardz.similarproducts.commons.product;

import com.ismardz.similarproducts.commons.exceptions.ProductClientException;
import com.ismardz.similarproducts.commons.product.model.ProductDetail;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductFacadeTest {

    @Mock
    private ProductClient productClient;
    @InjectMocks
    private ProductFacade productFacade;

    @Test
    void getProductDetail_returnsProductDetail() {
        String productId = "1";
        ProductDetail detail = new ProductDetail("1", "Product 1", 100.0, true);
        when(productClient.getProductDetail(productId)).thenReturn(detail);
        ProductDetail result = productFacade.getProductDetail(productId);
        assertNotNull(result);
        assertEquals(detail, result);
    }

    @Test
    void getProductDetail_throwsProductClientExceptionOnFeignException() {
        String productId = "1";
        when(productClient.getProductDetail(productId)).thenThrow(mock(FeignException.class));
        assertThrows(ProductClientException.class, () -> productFacade.getProductDetail(productId));
    }

    @Test
    void getSimilarProductIds_returnsList() {
        String productId = "1";
        List<String> ids = List.of("2", "3");
        when(productClient.getSimilarProductIds(productId)).thenReturn(ids);
        List<String> result = productFacade.getSimilarProductIds(productId);
        assertNotNull(result);
        assertEquals(ids, result);
    }

    @Test
    void getSimilarProductIds_throwsProductClientExceptionOnFeignException() {
        String productId = "1";
        when(productClient.getSimilarProductIds(productId)).thenThrow(mock(FeignException.class));
        assertThrows(ProductClientException.class, () -> productFacade.getSimilarProductIds(productId));
    }
}

