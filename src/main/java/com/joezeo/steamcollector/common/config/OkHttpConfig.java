package com.joezeo.steamcollector.common.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/16 11:36
 */
@Slf4j
@Configuration
public class OkHttpConfig {
    @Bean("client4Steam")
    public OkHttpClient initClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(chain -> {
                    /*设置延迟时间delay 防止ip被ban*/
                    int delay = 500;
                    try {
                        log.info("爬虫延迟设置 {} 毫秒", delay);
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return chain.proceed(chain.request());
                });

        return builder.build();
    }
}
