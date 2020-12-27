package com.example.demo;

import static java.lang.System.out;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
@Service
public class DemoService {

  @Async
  public CompletableFuture<String> helloAsync(String name) {
    out.println("In hello async service : " + Thread.currentThread().getName());
    try {
      Thread.sleep(1000);
    } catch (Exception ignore) {

    }
    return CompletableFuture.completedFuture(hello(name))
        .thenApply((greetings) -> {
          out.println(greetings + " : " + Thread.currentThread().getName());
          return greetings;
        });
  }

  public String hello(String name) {
    out.println("In hello service : " + Thread.currentThread().getName());
    return "Hello " + name;
  }

  @Async
  public ListenableFuture<String> greetingGoodMorningAsync(String name) {
    return AsyncResult.forValue(greetingGoodMorning(name));
  }

  public String greetingGoodMorning(String name) {
    out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
        " : In hello service -> greetingGoodMorning before : " + Thread.currentThread().getName());
    try {
      out.println("Sleep for 5 sec(s)");
      Thread.sleep(5000);
    } catch (Exception ignore) {

    }
    out.println("Awake after 5 sec(s)");
    out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        + " : In hello service -> greetingGoodMorning after : " + Thread.currentThread().getName());
    return "Good Morning " + name;
  }
}
