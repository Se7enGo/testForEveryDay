package day1019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestForPipeLine {


    @Test
    public void test01() {
        DefaultPipeline dp = new DefaultPipeline();
        dp.addFirst("NO.1", new DefaultHandle());
        dp.addFirst("NO.2", new DefaultHandle());
        dp.addFirst("NO.3", new DefaultHandle());
        dp.addFirst("NO.4", new DefaultHandle());
        System.out.println(dp.getCtxHandlers().size());
        dp.fireProcess();
    }

}
