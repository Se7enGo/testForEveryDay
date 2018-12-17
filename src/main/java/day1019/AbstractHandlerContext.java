package day1019;

public abstract class AbstractHandlerContext implements HandlerContext{

    // handler-context的前一个结点
    AbstractHandlerContext prev;

    // handler-context的下一个节点
    AbstractHandlerContext next;

    // handlerContext的名字
    private final String name;

    AbstractHandlerContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public HandlerContext fireProcess() {
        // 获取下一个上下文，如果子类不复写该方法，则直接传递到下一个处理节点
        AbstractHandlerContext next = this.next;

        if(next != null){
            //先执行 当前的handle 的内容
            next.invokeProcess();
            //再推动到下一个节点
            next.fireProcess();
        }
        return this;
    }

    private void invokeProcess() {
        handler().process(this);
    }

}
