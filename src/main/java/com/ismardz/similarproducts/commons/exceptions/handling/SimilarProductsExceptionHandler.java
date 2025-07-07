package com.ismardz.similarproducts.commons.exceptions.handling;

import com.ismardz.similarproducts.commons.exceptions.ProductClientException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;

@ControllerAdvice
@Slf4j
public class SimilarProductsExceptionHandler {

    @ExceptionHandler(ProductClientException.class)
    public ResponseEntity<ErrorResponse> handleProductClientException(final ProductClientException ex) {
        log.error("ProductClientException: {}", ex.getMessage(), ex);
        int status = Optional.ofNullable(ex.getHttpException())
            .map(FeignException::status)
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(status)
            .body(new ErrorResponse(ErrorEnum.PRODUCT_API_ERROR.getErrorCode(), ErrorEnum.PRODUCT_API_ERROR.getErrorMsg()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(final NoResourceFoundException ex) {
        log.error("NoResourceFoundException: {}", ex.getMessage(), ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(final Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
            .body(new ErrorResponse(ErrorEnum.UNEXPECTED_ERROR.getErrorCode(), ErrorEnum.UNEXPECTED_ERROR.getErrorMsg()));
    }
}