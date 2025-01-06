package com.commons.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse <T>{

    private int totalPages;

    private int currentPage;

    private long totalRecord;

    List<T> data;
}
