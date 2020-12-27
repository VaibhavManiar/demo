package com.example.demo;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static java.lang.System.out;

@RestController
public class DemoController {

  @Autowired
  private DemoService service;

  @GetMapping("/api/async/hello/{name}")
  public CompletableFuture<String> helloAsync(@PathVariable("name") String name) {
    CompletableFuture<String> cs = service.helloAsync(name);
    out.println("Free Main Thread : " + Thread.currentThread().getName());
    return cs;
  }


  @GetMapping("/api/hello/{name}")
  @ResponseBody
  public String hello(@PathVariable("name") String name) {
    out.println("Free Main Thread : " + Thread.currentThread().getName());
    return service.hello(name);
  }
}
