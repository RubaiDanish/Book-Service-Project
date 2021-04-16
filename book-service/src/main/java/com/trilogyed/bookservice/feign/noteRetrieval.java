package com.trilogyed.bookservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
// The @FeignClient marks this interface as a Feign client.
// The name attribute is the name of the service this client will interact with.
// This name must match the name of a service registered with Eureka.
@FeignClient(name = "note-service")
public interface noteRetrieval {

    // The @RequestMapping annotation allows us to specify which endpoint should be called when the annotated method is invoked.
    // In our case, a GET request will be issued to /books/note on the book-service whenever getNotes is invoked in our code.
    @RequestMapping(value = "/books/{note}", method = RequestMethod.GET)
    public Map<String, Integer> retrieveNotesById(@PathVariable int id);
}
