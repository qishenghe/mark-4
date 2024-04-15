package com.qishenghe.mark.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class CalThreadPool {

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("ThreadPool-%d").build();

    public static ExecutorService threadPool = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

}
