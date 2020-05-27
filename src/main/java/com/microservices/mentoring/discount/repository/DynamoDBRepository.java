package com.microservices.mentoring.discount.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class DynamoDBRepository {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${db_table}")
    private String tableName;


    private DynamoDBMapper mapper;

    @PostConstruct
    public void initLocal() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
        mapper = new DynamoDBMapper(client);
    }

    public <T> T findPromocode(Class<T> clazz, String code) {
        T result = mapper.load(clazz, code, DynamoDBMapperConfig.builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName)));
        return result;
    }


}
