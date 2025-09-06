package com.starter.lib.config.restTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;

@Slf4j
@Configuration
public class RestTemplateConfig {
    /**
     * 로깅을 위한 인터셉터 (선택 사항)
     */
    @Bean
    public ClientHttpRequestInterceptor loggingInterceptor() {
        return (request, body, execution) -> {
            // 요청 로깅
            log.info("Request URI: {}", request.getURI());
            log.info("Request Method: {}", request.getMethod());

            // 요청 실행
            return execution.execute(request, body);
        };
    }

//    /**
//     * 로깅 인터셉터가 포함된 RestTemplate (선택 사항)
//     */
//    @Bean
//    public RestTemplate loggingRestTemplate(RestTemplateBuilder builder, ClientHttpRequestInterceptor loggingInterceptor) {
//        return builder
//                .requestFactorySettings((requestFactorySettings)->requestFactorySettings.withConnectTimeout(Duration.ofSeconds(30)).withReadTimeout(Duration.ofSeconds(10)))
//                .additionalInterceptors(loggingInterceptor)
//                .build();
//    }
}
