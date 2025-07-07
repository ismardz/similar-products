package com.ismardz.similarproducts.commons.exceptions;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductClientException extends RuntimeException {

    private final FeignException httpException;
}
