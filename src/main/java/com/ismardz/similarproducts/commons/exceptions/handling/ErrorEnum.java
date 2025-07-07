package com.ismardz.similarproducts.commons.exceptions.handling;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorEnum {

    UNEXPECTED_ERROR(000, "Unexpected error occurred"),
    PRODUCT_API_ERROR(001, "Error calling product API");

    private final int errorCode;
    private final String errorMsg;
}
