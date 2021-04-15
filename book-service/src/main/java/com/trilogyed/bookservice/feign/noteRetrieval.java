package com.trilogyed.bookservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name = "note-service")
public interface noteRetrieval {

    @RequestMapping("/books/{note}")
    Map<String, Integer> retrieveNotesById(@PathVariable int id);
}
