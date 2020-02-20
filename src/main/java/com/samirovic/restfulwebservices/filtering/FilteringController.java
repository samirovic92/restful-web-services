package com.samirovic.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.samirovic.restfulwebservices.model.FilterBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filter")
    public MappingJacksonValue retrieveFilter(){

        FilterBean filterBean = new FilterBean("value11", "value11", "value11");

        SimpleBeanPropertyFilter simpleBeanFilter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("filterBean", simpleBeanFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(filterBean);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/all-filter")
    public List<FilterBean> retrieveAllFilter(){
        return Arrays.asList(
                new FilterBean("value11", "value11", "value11"),
                new FilterBean("value21", "value21", "value21"),
                new FilterBean("value31", "value31", "value31"));
    }
}
