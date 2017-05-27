import redis.clients.jedis.Jedis;

/**
 * Created by l on 2017/5/20.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.149");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
    }
}
