package com.user.service.Impl;

import com.commons.utility.SearchRequest;
import com.user.service.UserUtilsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserUtilsServiceImpl implements UserUtilsService {

    private int defaultSize = 10;

    @Override
    public PageRequest preparePageableRequest(SearchRequest searchRequest) {
        int pageSize = searchRequest.getSize() == 0 ? defaultSize : searchRequest.getSize();
        int pageNumber = searchRequest.getPage();
        Sort sortBy = Sort.by(searchRequest.getSortBy() == null ? getDefaultSort() : prepareSort(searchRequest.getSortBy()));
        return PageRequest.of(pageNumber,pageSize,sortBy);
    }

    // asc:version,desc:modified_date
    private List<Sort.Order> prepareSort(String sortBy) {
       List<Sort.Order> orders = new ArrayList<>();
       String[] split = sortBy.split(",");
       for(String str : split){
           String[] orderProperty = str.split(":");
           String order = orderProperty[0];
           String property = orderProperty[1];
           orders.add(new Sort.Order(Sort.Direction.ASC.equals(order) ? Sort.Direction.ASC : Sort.Direction.DESC,property));
       }
       return orders;
    }

    private List<Sort.Order> getDefaultSort() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order( Sort.Direction.ASC,"modified_date"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"version"));
        return orders;
    }
}
