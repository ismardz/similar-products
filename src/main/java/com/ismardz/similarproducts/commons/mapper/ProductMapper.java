package com.ismardz.similarproducts.commons.mapper;

import com.ismardz.similarproducts.commons.product.model.ProductDetail;
import com.ismardz.similarproducts.service.model.SimilarProductDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


  List<SimilarProductDetail> toSimilarProductDetail(List<ProductDetail> productDetail);

  SimilarProductDetail toSimilarProductDetail(ProductDetail productDetail);

}
