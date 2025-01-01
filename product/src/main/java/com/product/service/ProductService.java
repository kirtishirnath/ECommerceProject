package com.product.service;

import com.commons.product.ProductDto;
import com.commons.utility.PageableResponse;
import com.commons.utility.SearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService extends GenericService<ProductDto> {

    PageableResponse<ProductDto> search(SearchRequest searchRequest);
}
