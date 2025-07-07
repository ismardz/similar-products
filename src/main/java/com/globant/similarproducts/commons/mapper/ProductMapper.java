package com.globant.similarproducts.commons.mapper;

import com.globant.similarproducts.commons.product.model.ProductDetail;
import com.globant.similarproducts.service.model.SimilarProductDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


  List<SimilarProductDetail> toSimilarProductDetail(List<ProductDetail> productDetail);

  SimilarProductDetail toSimilarProductDetail(ProductDetail productDetail);

}
