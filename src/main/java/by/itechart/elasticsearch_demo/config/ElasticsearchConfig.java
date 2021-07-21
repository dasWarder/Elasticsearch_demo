package by.itechart.elasticsearch_demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan(basePackages = "by.itechart.elasticsearch_demo.service")
@EnableElasticsearchRepositories(basePackages = "by.itechart.elasticsearch_demo.repository")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    private String esUrl;

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }


    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration config = ClientConfiguration.builder()
                                                        .connectedTo(esUrl)
                                                        .build();

        RestHighLevelClient client = RestClients.create(config).rest();

        return client;
    }
}
