package com.ismardz.similarproducts.service.model;

import java.util.List;

public record GetSimilarProductsOutput (List<SimilarProductDetail> similarProducts) {}