package day1019;

public interface PipeLine {

    PipeLine addFirst(String name, Handler handler);

    PipeLine fireProcess();

}
