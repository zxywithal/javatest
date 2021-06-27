package org.zxy.DependencyInjection;

public class ExampleBeanByCons {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public ExampleBeanByCons(
        AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
        this.beanOne = anotherBean;
        this.beanTwo = yetAnotherBean;
        this.i = i;
    }

    @Override
    public String toString() {
        return "ExampleBeanByCons{" +
                "beanOne=" + beanOne +
                ", beanTwo=" + beanTwo +
                ", i=" + i +
                '}';
    }
}