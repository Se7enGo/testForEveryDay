package day1019;

import java.util.HashMap;
import java.util.Map;

public final class DefaultPipeline implements PipeLine{


    final AbstractHandlerContext head;

    final AbstractHandlerContext tail;

    private Map<String, Handler> ctxHandlers = new HashMap<String, Handler>(4);

    public DefaultPipeline() {

        head = new HeadHandlerContext();
        tail = new TailHandlerContext();

        // 双向链表
        head.next = tail;
        tail.prev = head;
    }

    static final class HeadHandlerContext extends AbstractHandlerContext implements Handler {

        private static final String HEADNAME = "HEAD";

        HeadHandlerContext() {
            super(HEADNAME);
        }

        @Override
        public Handler handler() {
            return this;
        }

        @Override
        public void process(HandlerContext context) {
            System.out.println(this.getName());
        }

    }

    static final class TailHandlerContext extends AbstractHandlerContext implements Handler {

        private static final String TAILNAME = "TAIL";

        TailHandlerContext() {
            super(TAILNAME);
        }

        @Override
        public Handler handler() {
            return this;
        }

        @Override
        public void process(HandlerContext context) {
            System.out.println(this.getName());
        }

    }

    /**
     * 实现pipeline接口中的addFirst方法，将handler置为head
     */
    @Override
    public PipeLine addFirst(String name, Handler handler) {

        AbstractHandlerContext newCtx = new DefaultHandlerContext(name, handler);
        AbstractHandlerContext nextCtx = head.next;
        newCtx.prev = head;
        newCtx.next = nextCtx;

        head.next = newCtx;
        nextCtx.prev = newCtx;

        ctxHandlers.put(name, handler);
        return this;
    }

    public Map<String, Handler> getCtxHandlers() {
        return ctxHandlers;
    }

    public void setCtxHandlers(Map<String, Handler> ctxHandlers) {
        this.ctxHandlers = ctxHandlers;
    }

    /**
     * 触发处理流程
     */
    @Override
    public PipeLine fireProcess() {
        //从头开始触发流程
        head.fireProcess();
        return this;
    }

}
