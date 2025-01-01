package com.product.service;

import com.commons.utility.SearchRequest;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

public interface ProductUtilsService {
    PageRequest preparePageableRequest(SearchRequest searchRequest);
}
