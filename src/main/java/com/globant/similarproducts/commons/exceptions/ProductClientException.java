package com.globant.similarproducts.commons.exceptions;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductClientException extends RuntimeException {

    private FeignException httpException;
}
