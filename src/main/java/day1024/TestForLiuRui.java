package day1024;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TestForLiuRui{

    @Test
    public void test01(){

        List<Integer>  list = new ArrayList<>(1);
        int length = 1;
        for (int i= 0;i<=list.size()/length -1;i++){
            list.subList(i*length, (i+1)*length >= list.size()? (i+1)* length: list.size());
        }
    }
}
