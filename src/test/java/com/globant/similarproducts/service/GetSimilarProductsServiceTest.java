package com.globant.similarproducts.service;

import com.globant.similarproducts.commons.mapper.ProductMapperImpl;
import com.globant.similarproducts.commons.product.ProductFacade;
import com.globant.similarproducts.commons.product.model.ProductDetail;
import com.globant.similarproducts.service.model.GetSimilarProductsOutput;
import com.globant.similarproducts.service.model.SimilarProductDetail;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSimilarProductsServiceTest {

    @Mock
    private ProductFacade productFacade;
    @Spy
    private ProductMapperImpl productMapper;
    @InjectMocks
    private GetSimilarProductsService getSimilarProductsService;

    @Test
    void testGetSimilarProducts_ReturnsSimilarProducts() {
        String productId = "1";
        List<String> similarIds = List.of("2", "3");
        ProductDetail detail1 = new ProductDetail("2", "Product 2", 100.0, true);
        ProductDetail detail2 = new ProductDetail("3", "Product 3", 200.0, false);
        SimilarProductDetail spd1 = new SimilarProductDetail("2", "Product 2", 100.0, true);
        SimilarProductDetail spd2 = new SimilarProductDetail("2", "Product 3", 200.0, false);
        when(productFacade.getSimilarProductIds(productId)).thenReturn(similarIds);
        when(productFacade.getProductDetail("2")).thenReturn(detail1);
        when(productFacade.getProductDetail("3")).thenReturn(detail2);
        when(productMapper.toSimilarProductDetail(List.of(detail1, detail2))).thenReturn(List.of(spd1, spd2));

        GetSimilarProductsOutput output = getSimilarProductsService.getSimilarProducts(productId);

        assertNotNull(output);
        assertEquals(2, output.similarProducts().size());
        verify(productMapper, times(2)).toSimilarProductDetail(anyList());
    }

    @Test
    void testGetSimilarProducts_EmptySimilarIds() {
        String productId = "1";
        when(productFacade.getSimilarProductIds(productId)).thenReturn(Collections.emptyList());

        GetSimilarProductsOutput output = getSimilarProductsService.getSimilarProducts(productId);

        assertNotNull(output);
        assertTrue(output.similarProducts().isEmpty());
    }

    @Test
    void testGetSimilarProducts_ProductDetailThrowsException() {
        String productId = "1";
        List<String> similarIds = List.of("2", "3");
        ProductDetail detail1 = new ProductDetail("2", "Product 1", 100.0, true);
        when(productFacade.getSimilarProductIds(productId)).thenReturn(similarIds);
        when(productFacade.getProductDetail("2")).thenReturn(detail1);
        when(productFacade.getProductDetail("3")).thenThrow(
            new FeignException.FeignClientException(404, "Not Found", Request.create(Request.HttpMethod.GET, "", new HashMap<>(), null, null, null), null, null));

        GetSimilarProductsOutput output = getSimilarProductsService.getSimilarProducts(productId);

        assertNotNull(output);
        assertEquals(1, output.similarProducts().size());
        verify(productMapper, times(1)).toSimilarProductDetail(anyList());
    }
}

