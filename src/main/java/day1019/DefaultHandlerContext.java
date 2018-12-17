package day1019;

public class DefaultHandlerContext extends AbstractHandlerContext {



    private Handler handler;

    DefaultHandlerContext(String name, Handler handler) {
        super(name);
        this.handler = handler;
    }

    @Override
    public Handler handler() {
        return handler;
    }

}
