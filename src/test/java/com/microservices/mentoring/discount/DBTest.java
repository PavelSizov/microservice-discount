package com.microservices.mentoring.discount;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.microservices.mentoring.discount.domain.Promocode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

@Disabled
public class DBTest {

    private static DynamoDB dynamoDB;

    private String tableName = "Promocode";

    private static DynamoDBMapper mapper;

    @BeforeAll
    public static void init() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "eu-central-1"))
                .build();
        dynamoDB = new DynamoDB(client);
        mapper = new DynamoDBMapper(client);
    }

    @Test
    public void createTableTest() throws InterruptedException {
        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                    Collections.singletonList(new KeySchemaElement("code", KeyType.HASH)), // Sort key
                    Collections.singletonList(new AttributeDefinition("code", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L, 10L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void addElementToTableTest() {
        Table table = dynamoDB.getTable(tableName);
        PutItemOutcome outcome = table.putItem(new Item()
                .withPrimaryKey("code", "01-2")
                .with("discount", BigDecimal.valueOf(0.21)));
//                .withString("discount", "0.15"));

        System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
    }

    @Test
    public void getElementTest() {
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<QueryOutcome> items = table.query("code", "01-2");

        for (Item item : items) {
            System.out.println(item.getString("discount"));
        }
    }

    @Test
    public void removeTableTest() {
        Table table = dynamoDB.getTable(tableName);

        table.delete();
        System.out.println("Success.  Table status: " + table.getDescription());
    }

    @Test
    public void findWithMapperTest() {
        Promocode promocode = mapper.load(Promocode.class, "01-2-");

        System.out.println(promocode);
    }
}
