package com.product.service.Impl;

import com.commons.utility.SearchRequest;
import com.product.service.ProductUtilsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUtilsServiceImpl implements ProductUtilsService {

    private int defaultSize = 10;

    @Override
    public PageRequest preparePageableRequest(SearchRequest searchRequest) {
        int pageSize,page;
        pageSize = searchRequest.getSize() == 0 ? defaultSize : searchRequest.getSize();
        page = searchRequest.getPage() == 0 ? 0 : searchRequest.getPage();
        Sort sort = searchRequest.getSortBy() == null ? Sort.by(getDefaultSortBy()) : Sort.by(prepareSortBy(searchRequest.getSortBy()));
        return PageRequest.of(page,pageSize,sort);
    }

    // asc:modifiedDate,desc:version
    private List<Sort.Order> prepareSortBy(String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] strs = sortBy.split(",");
        for(String str : strs){
            String[] directionProperty = str.split(":");
            String direction = directionProperty[0].toUpperCase();
            String property = directionProperty[1];
            orders.add(new Sort.Order(direction.equals(Sort.Direction.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC ,property));
        }
        return orders;
    }

    private List<Sort.Order> getDefaultSortBy() {
       List<Sort.Order> orders = new ArrayList<>();
       orders.add(new Sort.Order(Sort.Direction.ASC,"modified_date"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"version"));
        return orders;
    }
}
