package com.commons.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

   private String filter;

   private int size;

   private int page;

   private String sortBy;
}
