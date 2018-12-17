package test0925;


import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
public class TestForLambda {

    @Test
    public void test001() {
        System.out.printf("2222");
    }

    /**
     * 测试生成100万个UUID 取后面16位是否会重复
     */
    @Test
    public void getRandomUUID(){
        List<UUID> list = null;
        List<String> strings = null;
        list = Stream.generate(UUID::randomUUID).limit(10000000).collect(Collectors.toList());
        strings = list.parallelStream().map( u -> {
            return  u.toString().replaceAll("-","").substring(15);
        }).collect(Collectors.toList());
        long s = strings.stream().distinct().count();
        System.out.println(s);
    }

    @Test
    public void getRandomUUID2(){
        List<UUID> list = null;
        List<String> strings = null;
        list = Stream.generate(UUID::randomUUID).limit(1000).collect(Collectors.toList());
        strings = list.parallelStream().map( u -> {
            return  u.toString().replaceAll("-","").substring(15);
        }).collect(Collectors.toList());
        long s = strings.stream().distinct().count();
        System.out.println(s);

        //strings.stream().forEach(System.out::println);
    }

    @Test
    public void getRandom15UUID(){
        String uuid = "";
        String result = "";
        Pattern pattern = Pattern.compile("[a-z]\\w{14}");
        boolean bnn = true;
        while(bnn){
            uuid = UUID.randomUUID().toString();
            System.out.println("原始uuid:"+uuid);
            Matcher matcher = pattern.matcher(uuid);
            if (matcher.find()){
                result = matcher.group(0);
                bnn = true;
            }
        }
        System.out.println("结果uuid:"+result);
    }

    @Test
    public void testForPatternMather(){
        Pattern pattern = Pattern.compile("[a-z]{1}\\w{14}");
        String uuid = "";

        String result = "";
        Matcher matcher = null;
        boolean bn = true;
        do {
            uuid =  UUID.randomUUID().toString().replaceAll("-","");
            matcher = pattern.matcher(uuid);
            System.out.println("原始uuid:"+uuid);
            if(matcher.find()){
                result = matcher.group(0);
                bn = false;
            }
        }while(bn);

        System.out.println("匹配uuid:"+result);
    }

    @Test
    public void getAnything() {
        String ss = "Hello World!";
        char[] ch = ss.toCharArray();
        List<Character> characterList = new ArrayList<>();
        for (char c : ch) {
            characterList.add(c);
        }

        Stream characterStream = characterList.stream();
        //characterStream.forEach( a -> System.out.println(String.valueOf(a)));
        characterStream
                .filter(a -> String.valueOf(a).compareTo("c") > 0)
                .forEach(b -> System.out.println(b));
        //Arrays.stream();
       /* characterStream.filter(b - > StringUtils.containsWhitespace(b))
                .forEach( a -> String.valueOf(a).toUpperCase());*/

        //Stream<Character> stream =  Arrays.asList("s","s").stream();
        // Stream<Character> stream =  Stream.of(ch);
    }

    @Test
    public void testForqq() {

        final ArrayList<Map<String, Integer>> ss = new ArrayList();
        final  Map<String,Integer>  result = new HashMap<>();
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

        //1.声明一个 List<Map<String,List<String>>> list 这个 list key
        // 每个map 要拿过来  放入一个list里面 key 做key  value 插入到

        final List<Map<String,List<Integer>>> list = new ArrayList<>();
        //final Map<String,List<Integer>> map = new TreeMap<>((a,b) -> b.compareTo(a));

        final Map<String,List<Integer>> map = new TreeMap<>((a,b)->a.compareTo(b));
        list.add(map);

        ss.stream()
                .flatMap((a) -> TestForLambda.getQQSteam(list,a))
                .skip(ss.size()-1)
                .forEach(System.out::println);

       // Supplier<Map<String,Object>> supplier = ()->new HashMap<String,Object>();


        //Map map = ss.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        //System.out.println(map);
        //ss.stream().forEach(map -> map.forEach( (Map.Entry a) ->result.put((String)a.getKey(),a.getValue())));
        // this.ss.stream().filter();
        // this.ss.add((a,b) -> {return new Map<>().put(a,b);});
    }

    private static Stream<Map<String,List<Integer>>> getQQSteam(List<Map<String,List<Integer>>> list ,Map<String,Integer> map){
        Map<String,List<Integer>> m = list.get(0);
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            if(m.containsKey(key)){
                List<Integer> stringList = m.get(key);
                stringList.add( entry.getValue());
                stringList.sort((a,b) ->b.compareTo(a) );
            }else{
                List<Integer> stringList = new ArrayList<>();
                stringList.add( entry.getValue());
                stringList.sort((a,b) ->b.compareTo(a) );
                m.put(key,stringList);
            }
        }

        return list.stream();

    }


    private List<Employee> employees;
    public void initEmployeeList(){
        this.employees = new ArrayList<>();

        this.employees.add( new Employee("101","林青霞", 28, 9889.99, null));
        this.employees.add( new Employee("102","东方不败", 29, 4329.85, null));
        this.employees.add(new Employee("103","周星驰", 40, 1233.88, null));
        this.employees.add(new Employee("104","大圣", 500, 5000.44, null));
        this.employees.add(new Employee("105","张无忌", 15, 3000.09, null));
        this.employees.add( new Employee("102","东方不败", 29, 4329.85, null));

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Test
    public void testLamdba(){
        TestForLambda test = new TestForLambda();
        test.initEmployeeList();

        List<Employee> employees = test.getEmployees();

        System.out.println("filter 测试开始");
        employees.stream()
                .filter(a -> a.getAge() > 30)
                .forEach(System.out::println);
        System.out.println("filter 测试结束");
        System.out.println("-----------------------");


        System.out.println("distinct 测试开始");
        employees.stream()
                .distinct()
                .forEach(System.out::println);
        System.out.println("distinct 测试结束");
        System.out.println("-----------------------");


        System.out.println("map 测试开始");
        employees.stream()
                .map(x -> x.getAge() > 30)
                .forEach(System.out::println);
        System.out.println("map 测试结束");
        System.out.println("-----------------------");

    }



    private static Stream<Character> filterCharacter(String str) {
        List<Character> characters = new ArrayList<>();

        for (Character character : str.toCharArray()) {
            characters.add(character);
        }
        return characters.stream();
    }

    @Test
    public void test8() {
        List<String> list = Arrays.asList("aaa", "hello world");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestForLambda::filterCharacter);

        streamStream.forEach((s) -> {
            s.forEach((c) -> System.out.println(c + ""));
            System.out.println();
        });

        System.out.println("----------------------");

        list.stream()
                .flatMap(TestForLambda::filterCharacter)
                .forEach(System.out::println);


    }

}
