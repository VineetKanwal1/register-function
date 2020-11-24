package com.techprimers.serverless;

import java.util.HashMap;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class RegisterUserHandler implements RequestHandler<RegisterUser, RegisterUserResponse> {

	AmazonDynamoDB ddb;
	private String DYNAMODB_TABLE_NAME = "User";
	private Regions REGION = Regions.US_EAST_2;

	@Override
	public RegisterUserResponse handleRequest(RegisterUser user, Context context) {
		this.initDynamoDbClient();
		persistData(user);
		RegisterUserResponse registrationresponse = new RegisterUserResponse();
		registrationresponse.setMessage("User with ID "+ user.getUserId() + " registered successfuly!");
		return registrationresponse;
	}

	private void persistData(RegisterUser user) throws ConditionalCheckFailedException {

		HashMap<String, AttributeValue> values = new HashMap<String, AttributeValue>();

		values.put("userId", new AttributeValue(user.getUserId()));
		values.put("password", new AttributeValue(user.getPassword()));
		values.put("address", new AttributeValue(user.getAddress()));

		this.ddb.putItem(DYNAMODB_TABLE_NAME, values);
	}

	@SuppressWarnings("deprecation")
	private void initDynamoDbClient() {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));		
		this.ddb = AmazonDynamoDBClientBuilder.defaultClient();
	}
}
