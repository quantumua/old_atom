package com.betamedia.atom.app.config;

import com.betamedia.atom.app.entity.TestInformation;
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
public class TestInformationCacheConfig {
    @Bean
    public LoadingCache<UUID, TestInformation> testInformationCache() {
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build(new CacheLoader<UUID, TestInformation>() {
                    @Override
                    public TestInformation load(UUID key) {
                        return null;
                    }
                });
    }
}
