package com.example.demo;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  @Async
  public CompletableFuture<String> helloAsync(String name) {
    System.out.println("In hello async service : " + Thread.currentThread().getName());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignore) {
      ignore.printStackTrace();
    }
    return CompletableFuture.completedFuture(hello(name))
        .thenApply((greetings) -> {
          System.out.println(greetings + " : " + Thread.currentThread().getName());
          return greetings;
        });
  }

  public String hello(String name) {
    System.out.println("In hello service : " + Thread.currentThread().getName());
    return "Hello " + name;
  }
}
