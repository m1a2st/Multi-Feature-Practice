package com.example.config;

import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.api.IndexApi;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.api.UtilsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://localhost:9308");
        return apiClient;
    }

    @Bean
    public IndexApi indexApi() {
        return new IndexApi(apiClient());
    }

    @Bean
    public SearchApi searchApi() {
        return new SearchApi(apiClient());
    }

    @Bean
    public UtilsApi utilsApi() {
        return new UtilsApi(apiClient());
    }

}
