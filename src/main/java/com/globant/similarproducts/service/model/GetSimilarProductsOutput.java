package com.globant.similarproducts.service.model;

import java.util.List;

public record GetSimilarProductsOutput (List<SimilarProductDetail> similarProducts) {}