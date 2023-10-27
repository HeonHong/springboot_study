package hello.proxy.pureproxy.decorator.code;

public abstract class AbstractDecorator {
    protected Component component;

    public AbstractDecorator(Component component) {
        this.component = component;
    }

    abstract String operation();

}
