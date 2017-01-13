package echoweb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import echoweb.bean.WebUserBean;
import echoweb.domain.WebUser;

/**
 * This service implementation using AWS dynamoDB.
 * It could use local instance but still need valid
 * AWS credentials
 * 
 * @author stevenwang
 *
 */

public class WebUserServiceImpl implements WebUserService {
	
	//static AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(
        //    new ProfileCredentialsProvider()).withEndpoint("http://localhost:8000");
	//static AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(
        //    new ProfileCredentialsProvider());
	static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
	
	static DynamoDBMapper mapper;

    static String tableName = "tbl_webuser";
    
    public WebUserServiceImpl() {
    	mapper = new DynamoDBMapper(dbClient);
    	createTable();
    	listMyTables();   	
    }


	public List<WebUserBean> getUserList() {
		// TODO Auto-generated method stub
		List<WebUserBean> users = new ArrayList<WebUserBean>();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		PaginatedScanList result = mapper.scan(WebUser.class, scanExpression);
		
		if(result != null) {
			Iterator its = result.iterator();
			while(its.hasNext()) {
				WebUser ur = (WebUser)its.next();
				WebUserBean user = new WebUserBean(ur.getEmail(), ur.getFirstName(), ur.getLastName());
				users.add(user);
			}			
		}
		
		return users;
	}

	public void updateUser(WebUserBean userBean) {
		WebUser user = new WebUser();
		user.setEmail(userBean.getEmail());
		user.setLastName(userBean.getLastName());
		user.setFirstName(userBean.getFirstName());
		mapper.save(user);
	}

	public void deleteUser(String email) {
		WebUser user = mapper.load(WebUser.class, email);
		mapper.delete(user);		
	}

	static void createTable() {

        try {

            ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("email")
                .withAttributeType("S"));

            ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
            keySchema.add(new KeySchemaElement()
                .withAttributeName("email")
                .withKeyType(KeyType.HASH)); //Partition key

            CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L));

            System.out.println("Issuing CreateTable request for " + tableName);
            Table table = dynamoDB.createTable(request);

            System.out.println("Waiting for " + tableName
                + " to be created...this may take a while...");
            table.waitForActive();

        } catch (Exception e) {
            System.err.println("CreateTable request failed for " + tableName);
            System.err.println(e.getMessage());
        }

    }

    static void listMyTables() {

        TableCollection<ListTablesResult> tables = dynamoDB.listTables();
        Iterator<Table> iterator = tables.iterator();

        System.out.println("Listing table names");

        while (iterator.hasNext()) {
            Table table = iterator.next();
            System.out.println(table.getTableName());
        }
    }
	
}
