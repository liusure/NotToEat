import com.notoeat.core.test.EhcacheTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by l on 2017/5/20.
 */
@ContextConfiguration(locations = { "classpath:testapplicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EhcacheTest {
    @Resource
    private EhcacheTestService ehcacheTestService;
    @Test
    public void getTimestampTest() throws InterruptedException{
        System.out.println("第一次调用：" + ehcacheTestService.cacheTest("param"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehcacheTestService.cacheTest("param"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + ehcacheTestService.cacheTest("param"));
    }
}
