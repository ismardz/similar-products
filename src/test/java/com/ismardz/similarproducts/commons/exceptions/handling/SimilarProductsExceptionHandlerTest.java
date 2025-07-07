package com.ismardz.similarproducts.commons.exceptions.handling;

import com.ismardz.similarproducts.commons.exceptions.ProductClientException;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimilarProductsExceptionHandlerTest {

    @InjectMocks
    private SimilarProductsExceptionHandler handler;

    @Test
    void handleProductClientException_withStatus() {
        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(HttpStatus.NOT_FOUND.value());
        ProductClientException ex = new ProductClientException(feignException);
        ResponseEntity<?> response = handler.handleProductClientException(ex);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void handleProductClientException_withoutStatus() {
        ProductClientException ex = new ProductClientException(null);
        ResponseEntity<?> response = handler.handleProductClientException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void handleNoResourceFoundException_returnsNotFound() {
        NoResourceFoundException ex = mock(NoResourceFoundException.class);
        ResponseEntity<?> response = handler.handleNoResourceFoundException(ex);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void handleGenericException_returnsInternalServerError() {
        Exception ex = new Exception("error");
        ResponseEntity<?> response = handler.handleGenericException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}

