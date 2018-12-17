package test0925;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
public class TestForLambda {

    @Test
    public void test001() {
        System.out.printf("2222");
    }


    @Test
    public void testForPatternMather(){
        /*Pattern pattern = Pattern.compile("[a-z]\\w{14}");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String result = "a12345678901234";
        Matcher matcher = pattern.matcher(result);*/
        System.out.println("我要测试了");
    }
    //首字母大写 并小写其他部分
    @Test
    public void test002() {

        String[] str = new String[]{"adam", "LISA", "barT"};
        Stream<String> streamM = Stream.of(str);

        //streamM.forEach( System.out::println);

        streamM.map(a -> {
            StringBuilder sb = new StringBuilder("");
            sb.append(a.substring(0, 1).toUpperCase());
            sb.append(a.substring(1, a.length()).toLowerCase());
            return sb.toString();
        }).forEach(System.out::println);
    }

    //规约求积
    @Test
    public void testForReduce() {
        int[] ints = new int[]{32, 32, 1, 3, 5421, 7, 51, 31};
        IntStream intStream = Arrays.stream(ints);
        OptionalDouble optionalDouble = intStream.mapToDouble(Double::valueOf).reduce((a, b) -> {
            return a * b;
        });
        System.out.println(BigDecimal.valueOf(optionalDouble.getAsDouble()));

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
        final Map<String, Integer> result = new HashMap<>();
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

        final List<Map<String, List<Integer>>> list = new ArrayList<>();
        //final Map<String,List<Integer>> map = new TreeMap<>((a,b) -> b.compareTo(a));

        final Map<String, List<Integer>> map = new TreeMap<>((a, b) -> a.compareTo(b));
        list.add(map);

        ss.stream()
                .flatMap((a) -> TestForLambda.getQQSteam(list, a))
                .skip(ss.size() - 1)
                .forEach(System.out::println);

        ss.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        // Supplier<Map<String,Object>> supplier = ()->new HashMap<String,Object>();


        //Map map = ss.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        //System.out.println(map);
        //ss.stream().forEach(map -> map.forEach( (Map.Entry a) ->result.put((String)a.getKey(),a.getValue())));
        // this.ss.stream().filter();
        // this.ss.add((a,b) -> {return new Map<>().put(a,b);});
    }

    private static Stream<Map<String, List<Integer>>> getQQSteam(List<Map<String, List<Integer>>> list, Map<String, Integer> map) {
        Map<String, List<Integer>> m = list.get(0);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            if (m.containsKey(key)) {
                List<Integer> stringList = m.get(key);
                stringList.add(entry.getValue());
                stringList.sort((a, b) -> b.compareTo(a));
            } else {
                List<Integer> stringList = new ArrayList<>();
                stringList.add(entry.getValue());
                stringList.sort((a, b) -> b.compareTo(a));
                m.put(key, stringList);
            }
        }

        return list.stream();

    }


    private List<Employee> employees;

    public void initEmployeeList() {
        this.employees = new ArrayList<>();

        this.employees.add(new Employee("101", "林青霞", 28, 9889.99, null));
        this.employees.add(new Employee("102", "东方不败", 29, 4329.85, null));
        this.employees.add(new Employee("103", "周星驰", 40, 1233.88, null));
        this.employees.add(new Employee("104", "大圣", 500, 5000.44, null));
        this.employees.add(new Employee("105", "张无忌", 15, 3000.09, null));
        this.employees.add(new Employee("102", "东方不败", 29, 4329.85, null));

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Test
    public void testLamdba() {
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


    @Test
    public void testForForkJoin() {
        int [] a = new int[1000];
        for (int i = 0;i<a.length;i++){
            a[i] = Math.round(Math.round(Math.random()*1000));
        }


        ForkJoinPool pool = new ForkJoinPool();
        SortTask task = new SortTask(a,0,a.length-1);

        long st = System.currentTimeMillis();
        ForkJoinTask task1 = pool.submit(task);
        try {
            task1.get();
           // Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - st + "ms");
        /*for (int i = 0; i < a.length; i++)
            System.out.println(String.format("%4d",a[i] ));*/


    }

    static class SortTask extends RecursiveAction {

        private int start;
        private int end;
        private int[] ints;

        public int[] getInts() {
            return ints;
        }

        public void setInts(int[] ints) {
            this.ints = ints;
        }

        public SortTask(int[] ints, int start, int end) {
            this.ints = ints;
            this.start = start;
            this.end = end;
        }
        @Override
        protected void compute() {
            List result = new ArrayList();
            //1.判断 输入的参数是否正确

            //制定最外层的边界
            //推动边界往里推
            //退出边界
            //递归左边
            //递归右边
            System.out.println("当前运行线程："+Thread.currentThread().getName());
            int l = start;
            int r = end;
            if (start >= end)
                return ;
            int temp = ints[start];

            while (l != r) {
                while (l < r && ints[r] >= temp)
                    r--;
                if (r > l)
                    ints[l] = ints[r];//a[i]已经赋值给temp,所以直接将a[r]赋值给a[i],赋值完之后a[r],有空位
                while (l < r && ints[l] <= temp)
                    l++;
                if (l < r)
                    ints[r] = ints[l];
            }
            ints[l] = temp;//把基准插入,此时i与r已经相等R[low..pivotpos-1].keys≤R[pivotpos].key≤R[pivotpos+1..high].keys
            //Arrays.stream(ints).forEach(System.out::println);
            SortTask left = new SortTask(ints, start, l - 1);
            SortTask right = new SortTask(ints, l+1, end);
            //quickSort(a, left, l - 1);/*递归左边*/
            //quickSort(a, l + 1, right);/*递归右边*/

            left.fork();
            right.fork();

            left.join();
            right.join();

        }
    }

    static class CountTask extends RecursiveTask<Integer> {

        private static final int THREAD_HOLD = 2;

        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //如果任务足够小就计算
            boolean canCompute = (end - start) <= THREAD_HOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                CountTask left = new CountTask(start, middle);
                CountTask right = new CountTask(middle + 1, end);
                //执行子任务
                left.fork();
                right.fork();
                //获取子任务结果
                int lResult = left.join();
                int rResult = right.join();
                sum = lResult + rResult;
            }
            return sum;
        }

        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool();
            CountTask task = new CountTask(1, 4);
            Future<Integer> result = pool.submit(task);
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    public static void quickSort(int a[], int left, int right) {
        //System.out.println("left : "+ left + "right : "+ right);
        int l = left;
        int r = right;
        if (left >= right)
            return;
        int temp = a[left];

        while (l != r) {
            while (l < r && a[r] >= temp)
                r--;
            if (r > l)
                a[l] = a[r];//a[i]已经赋值给temp,所以直接将a[r]赋值给a[i],赋值完之后a[r],有空位
            while (l < r && a[l] <= temp)
                l++;
            if (l < r)
                a[r] = a[l];
        }
        a[l] = temp;//把基准插入,此时i与r已经相等R[low..pivotpos-1].keys≤R[pivotpos].key≤R[pivotpos+1..high].keys
        quickSort(a, left, l - 1);/*递归左边*/
        quickSort(a, l + 1, right);/*递归右边*/
    }

    @Test
    public void  testQuickSort() {
        //int [] a = new int[]{8, 2, 6, 12, 1, 9, 5, 5, 10};
        int [] a = new int[1000000];
        for (int i = 0;i<a.length;i++){
            a[i] = Math.round(Math.round(Math.random()*1000000));
        }
        int i;
        long st = System.currentTimeMillis();
        quickSort(a, 0, a.length-1);/*排好序的结果*/

        System.out.println(System.currentTimeMillis() - st + "ms");
        /*for (i = 0; i < 9; i++)
            System.out.println(String.format("%4d", a[i]));*/
    }

}
