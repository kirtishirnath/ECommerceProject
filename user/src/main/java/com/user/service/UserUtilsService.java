package com.user.service;

import com.commons.utility.SearchRequest;
import org.springframework.data.domain.PageRequest;

public interface UserUtilsService {
    PageRequest preparePageableRequest(SearchRequest searchRequest);
}
