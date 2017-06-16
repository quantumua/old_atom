package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
@Configuration
public class TestTaskCacheConfig {
    @Bean
    public LoadingCache<UUID, TestTask> taskCache() {
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .maximumSize(1000)
                .expireAfterWrite(300, TimeUnit.SECONDS)
                .build(new CacheLoader<UUID, TestTask>() {
                    @Override
                    public TestTask load(UUID key) {
                        //consider what to do for new key lookup
                        return null;
                    }
                });
    }
}
