import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 2017/5/20.
 */
public class MongoTest {
    public static void main(String[] args) {

//        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

        ServerAddress serverAddress = null;
        try {
            serverAddress = new ServerAddress("127.0.0.1");
            List<ServerAddress> address = new ArrayList<ServerAddress>();
            address.add(serverAddress);
            MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root","test","root".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(mongoCredential);
            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(address,credentials);
            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            mongoDatabase.createCollection("testList");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("test");
            mongoCollection.insertOne(new Document("tetle","mongodb").append("a","a"));
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
