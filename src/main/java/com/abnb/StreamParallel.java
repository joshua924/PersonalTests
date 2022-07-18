package com.abnb;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamParallel {

  public static void main(String[] args) {
    Supplier<Stream<Integer>> supplier = () -> IntStream
        .rangeClosed(1, 50)
        .boxed();
    printAndMeasure(supplier.get(), false);
    printAndMeasure(supplier.get(), true);
  }

  private static void printAndMeasure(Stream<Integer> stream, boolean parallel) {
    long start = System.currentTimeMillis();
    if (parallel) {
      stream = stream.parallel();
    }
    System.out.println(stream
        .map(new SlowProcessor())
        .collect(Collectors.toList()));
    long millis = System.currentTimeMillis() - start;
    System.out.printf("With parallel being %s, it took %d milliseconds%n", parallel, millis);
  }

  public static class SlowProcessor implements Function<Integer, Integer> {
    private final Random random = new Random();

    @Override
    public Integer apply(Integer integer) {
      try {
        int sleepTime = random.nextInt(1000) + 200;
        Thread.sleep(sleepTime);
      } catch (InterruptedException ignored) {
      }
      return 1 + integer;
    }
  }
}
