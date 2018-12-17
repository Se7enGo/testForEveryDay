package day914;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class TestForLambda {


    public static void main(String[] args) {

//        MathOperation operation = (int a,int b)->a+b;
//        SayMessage message = (String bbn ) -> System.out.printf(bbn);
//        message.saySomething(String.valueOf(operation.operate(1,22) + 2));

       // new TestForLambda().test1();

        new TestForLambda().testForNoLimitedStream();

    }

    interface MathOperation{
        int operate(int a, int b);
    }

    interface GreetingService{
        void sendMessage(String message);

    }

    interface  SayMessage{
        void saySomething(String str);
    }


    private ArrayList<Map<String,Object>>  ss = new ArrayList();

    public void initArray(){
        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("a",1);
        tempMap.put("c",3);

        Map<String,Object> tempMap2 = new HashMap<>();
        tempMap2.put("a",11);
        tempMap2.put("c",4);

        Map<String,Object> tempMap3 = new HashMap<>();
        tempMap3.put("a",111);

        Map<String,Object> tempMap4 = new HashMap<>();
        tempMap4.put("b",22);
        tempMap4.put("c",5);

        Map<String,Object> tempMap5 = new HashMap<>();
        tempMap5.put("b",22);
        tempMap5.put("c",5);



        this.ss.add(tempMap);
        this.ss.add(tempMap2);
        this.ss.add(tempMap3);
        this.ss.add(tempMap4);
        this.ss.add(tempMap5);

       // this.ss.stream().filter();
       // this.ss.add((a,b) -> {return new Map<>().put(a,b);});
    }

    public void test1 (){

        Supplier ss;
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(50, (x) -> x + 2).limit(4);
        stream3.forEach(System.out::println);

        System.out.println("-------------");

        // 生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(4);
        stream4.forEach(System.out::println);
    }

    public void testForNoLimitedStream(){

        Stream<Double> ss = Stream.generate(Math::random).limit(10);
        ss.forEach(System.out::println);

        Double dd = Math.random()*10;
        dd.floatValue();
    }

    public void testForCreateStream(){
        String ss = "Hello World!";

        Stream<Character> stream =  Arrays.asList('s','s').stream();
    }
}

