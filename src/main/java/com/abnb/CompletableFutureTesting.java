package com.abnb;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTesting {
    /**
     * CompletableFuture.completedFuture() method can't pass exceptions along if they occur during the evaluation of
     * the parameter. Instead, the exception will be thrown outside of the completable future. In order for it to be a
     * completable future that finishes exceptionally, we need to use CompletableFuture.supplyAsync().
     */
    public static void main(String[] args) {
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
}
