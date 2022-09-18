package com.first.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Sir 豪
 * 2022/4/28 0028
 * @Async  配合使用
 */
@Configuration
@EnableAsync
@Slf4j
public class SyncConfiguration implements AsyncConfigurer {

    /**
     * 自定义线程池
     * @return
     */
    @Bean("asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        //  //线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        threadPoolTaskExecutor.setMaxPoolSize(50);
        //缓存队列
        threadPoolTaskExecutor.setQueueCapacity(20);
        //空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        threadPoolTaskExecutor.setKeepAliveSeconds(1);
        //异步方法内部线程名称
        threadPoolTaskExecutor.setThreadNamePrefix("async-");
        /**
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
         * 通常有以下四种策略：
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：让调用者运行任务
         */
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;

    }
    /**
     * 指定默认线程池->实现AsyncConfigurer
     */
    @Override
    public Executor getAsyncExecutor() {
       // return AsyncConfigurer.super.getAsyncExecutor();
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) ->
                log.error("线程池执行任务发送未知错误,执行方法：{}",method.getName(),ex);
      //  return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
