package day1019;

public class DefaultHandle implements Handler {
    @Override
    public void process(HandlerContext context) {
        System.out.println("this handleContext name is "+ context.toString());
    }
}
