package test0925;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class TestForLambdaDay1012 {


    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //2. 交易员都在哪些不同的城市工作过
    @Test
    public void test01(){
        transactions.stream()
                .map(a -> a.getTrader())
                .distinct()
                .map(a -> a.getCity())
                .distinct()
                .forEach(System.out::println);
    }

    //1. 找出2011年发生的所有交易， 并按交易额排序(从低到高)
    @Test
    public void test02(){
        transactions.stream()
                .filter(a -> a.getYear() == 2011)
                .sorted((a,b) -> Integer.compare(a.getValue(),b.getValue()))
                .forEach(System.out::println);
    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test03(){
        transactions.stream()
                .filter( a -> "Cambridge".equals(a.getTrader().getCity()))
                .map(a -> a.getTrader())
                .distinct()
                .sorted((a,b) -> a.getName().compareTo(b.getName()))
                .forEach(System.out::println);

    }

    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test06(){
       Optional<Integer> sum =  transactions.stream()
                .filter( a -> "Cambridge".equals(a.getTrader().getCity()))
                .map(a -> a.getValue())
                .reduce(Integer::sum);
        System.out.println(sum.get());

    }


    @Test
    public  void test08(){
        //int [] a = new int[1000000000];
        //System.out.println(a.length);
        //list.stream().forEach(a -> a+1);
        //Stream<Integer> integerStream = Stream.iterate(1000000000,x->x-1).limit(1000000000).parallel() ;
        //List<Integer> integerStream2 = Arrays.asList( 2,6,8,9,1);
        System.out.println(12|24);
        //boolean bn = integerStream.anyMatch(a -> integerStream2.contains(a));
        //System.out.println(bn);
    }

    @Test
    public void testForqq() {

        final ArrayList<Map<String, Integer>> ss = new ArrayList();

        Map<String, Integer> tempMap = new HashMap<>();
        tempMap.put("a", 11);
        tempMap.put("c", 3);

        Map<String, Integer> tempMap2 = new HashMap<>();
        tempMap2.put("a", 1);
        tempMap2.put("c", 4);

        Map<String, Integer> tempMap3 = new HashMap<>();
        tempMap3.put("a", 111);

        Map<String, Integer> tempMap4 = new HashMap<>();
        tempMap4.put("b", 322);
        tempMap4.put("c", 5);

        Map<String, Integer> tempMap5 = new HashMap<>();
        tempMap5.put("b", 22);
        tempMap5.put("c", 5);

        ss.add(tempMap);
        ss.add(tempMap2);
        ss.add(tempMap3);
        ss.add(tempMap4);
        ss.add(tempMap5);



        Map<String,List<Integer>> result = new TreeMap<>((a,b)->a.compareTo(b));
        ss.stream()
                .forEach(a -> {
                   a.keySet().stream()
                           .forEach( b -> {
                               if(result.containsKey(b)){
                                   List<Integer> ll = result.get(b);
                                   ll.add(a.get(b));
                               }else{
                                   List<Integer> ll = new ArrayList<>();
                                   ll.add(a.get(b));
                                   result.put(b,ll);
                               }
                           });
                });
        result.values().stream().forEach(a -> Collections.sort(a));
        System.out.println(result);
    }
}

class Trader {
    private String name;
    private String city;

    public Trader() {
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // ...
    @Override
    public String toString() {
        return "Trader [name=" + name + ", city=" + city + "]";
    }
}

class Transaction {
    private Trader trader;
    private int year;
    private int value;

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Transaction() {
    }

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    // ...
    @Override
    public String toString() {
        return "Transaction [trader=" + trader + ", year=" + year + ", value=" + value + "]";
    }
}