package com.ccsw.tutorial.common.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageableRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNumber;

    private int pageSize;

    private List<SortRequest> sort;

    public PageableRequest() {

        sort = new ArrayList<>();
    }

    public PageableRequest(int pageNumber, int pageSize) {

        this();
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageableRequest(int pageNumber, int pageSize, List<SortRequest> sort) {

        this();
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SortRequest> getSort() {
        return sort;
    }

    public void setSort(List<SortRequest> sort) {
        this.sort = sort;
    }

    @JsonIgnore
    public Pageable getPageable() {

        return PageRequest.of(this.pageNumber, this.pageSize, Sort.by(sort.stream().map(e -> new Sort.Order(e.getDirection(), e.getProperty())).collect(Collectors.toList())));
    }

    public static class SortRequest implements Serializable {

        private static final long serialVersionUID = 1L;

        private String property;

        private Sort.Direction direction;

        protected String getProperty() {
            return property;
        }

        protected void setProperty(String property) {
            this.property = property;
        }

        protected Sort.Direction getDirection() {
            return direction;
        }

        protected void setDirection(Sort.Direction direction) {
            this.direction = direction;
        }
    }

}
