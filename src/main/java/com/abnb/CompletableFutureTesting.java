package com.abnb;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTesting {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testFutureExecution();
    }

    // when "get()" is used, the completable future becomes blocking
    private static void testFutureExecution() throws ExecutionException, InterruptedException {
        MockFuture.create(1).get();
        MockFuture.create(2).get();
        MockFuture.create(3).get();
    }

    /**
     * the exceptionally() method will not wrap or unwrap the exception.
     */
    private static void testWrapping() {
        CompletableFuture<Boolean> failedFuture = new CompletableFuture<>();
        failedFuture.completeExceptionally(new CompletionException(new IllegalArgumentException(new ArithmeticException())));

        failedFuture.exceptionally(
                e -> {
                    System.out.println(e.toString());
                    System.out.println(e.getLocalizedMessage());
                    System.out.println(e.getMessage());
                    return false;
                }
        ).join();
    }

    /**
     * CompletableFuture.completedFuture() method can't pass exceptions along if they occur during the evaluation of
     * the parameter. Instead, the exception will be thrown outside of the completable future. In order for it to be a
     * completable future that finishes exceptionally, we need to use CompletableFuture.supplyAsync().
     */
    private static void testExecution() {
        CompletableFuture<Boolean> policyCheck = CompletableFuture.supplyAsync(
                        CompletableFutureTesting::validate)
                .thenCompose(ignored -> CompletableFuture.completedFuture(true));
        System.out.println(policyCheck.handle(
                (response, exception) -> {
                    if (exception != null) {
                        return "exception";
                    }
                    return "through";
                }
        ).join());
    }

    private static boolean validate() {
        throw new IllegalArgumentException("bad input");
    }

    static class MockFuture {
        public static CompletableFuture<Integer> create(int res) {
            return CompletableFuture.supplyAsync(
                    () -> {
                        System.out.println("Into the future");
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            System.out.println("ah ah ah");
                        }
                        System.out.println("result " + res);
                        return res;
                    }
            );
        }
    }
}
