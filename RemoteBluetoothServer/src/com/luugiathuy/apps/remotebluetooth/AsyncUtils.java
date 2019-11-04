package com.luugiathuy.apps.remotebluetooth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class helper offering solution to execute any give operations in an asynchronous way thanks
 * to the usage of threads and threadpools. C# ThreadPool.QueueUserWorkItem() equivalent
 */
public final class AsyncUtils {
    private final static ExecutorService executor = 
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Execute the given runnable in a threadpool internally managed by a <code>ExecutorService</code>
     * @param runAsync  the runnable to run in an asynchronous operation
     * @throws  IllegalArgumentException in case the runnable is null
     * @see ExecutorService
     * @see Executors
     */
    public static void executeAsync(final Runnable runAsync) {
        if (runAsync == null)
            throw new IllegalArgumentException("runAsync MUST NOT be NULL");
        executor.execute(runAsync);
    }
    
    /**
     * Attempts to stop all actively executing tasks, halts the processing of 
     * waiting tasks, and returns a list of the tasks that were awaiting execution.
     * */
    public static void shutdownNow() {
    	executor.shutdownNow();
    }
}