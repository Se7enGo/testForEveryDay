package day1019;

public interface HandlerContext {

    Handler handler();

    HandlerContext fireProcess();
}
