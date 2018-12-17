package test0925;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class TestForStreamReduce {

    private final ArrayList<Map<String, Integer>> ss = new ArrayList();

    @Before
    public void initList() {
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
    }

    @Test
    public void test01() {
        /*
        //方法一
        Map<String,List<Integer>> result = new TreeMap<>((a, b)->a.compareTo(b));

       ss.stream()
                .map( a-> a.entrySet())
                .flatMap(b -> b.stream())
                .forEach( (a ) -> {
                    String key = a.getKey();
                    if(result.containsKey(key)){
                        List<Integer> ll = result.get(key);
                        ll.add(a.getValue());
                    }else{
                        List<Integer> ll = new ArrayList<>();
                        ll.add(a.getValue());
                        result.put(key,ll);
                    }
                });
        result.values()
                .forEach(a -> Collections.sort(a));
        System.out.println(result);*/

        //方法二
        /*Map<String, ArrayList<Integer>> map = new TreeMap<>();

        map = ss.stream()
                .map(a -> a.entrySet())
                .flatMap(b -> b.stream())
                .collect(Collectors.toMap(entry -> entry.getKey()
                , entry2 -> {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(entry2.getValue());
                    return list;
                },(a,b) -> {
                   a.addAll(b);
                   return a;
                        }));
        System.out.println(map);*/

        //方法三
        Map<String, List<Integer>> on = ss.stream()
                .map(a -> a.entrySet())
                .flatMap(b -> b.stream())
                .collect( Collectors.groupingBy(entry -> entry.getKey(), Collectors.mapping(t->t.getValue(),Collectors.toList())));
        //groupingBy 看来没有办法直接排序  需要放在TreeMap中
        TreeMap<String,List<Integer>> tm = new TreeMap<>((a,b) -> b.compareTo(a));
        tm.putAll(on);
        tm.entrySet().stream()
        .forEach(a -> Collections.sort(a.getValue(),Collections.reverseOrder()));
        System.out.println( tm);

    }

    @Test
    public void test02() {

        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0, 10000000000L).parallel().sum();
        System.out.println("sum : " + sum + "time : " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void test03(){

        System.out.println(Collections.disjoint(Arrays.asList("212","12"),Arrays.asList("21")));
    }
}
