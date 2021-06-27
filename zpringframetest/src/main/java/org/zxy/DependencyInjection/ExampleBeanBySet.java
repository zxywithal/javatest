package org.zxy.DependencyInjection;

public class ExampleBeanBySet {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public void setBeanOne(AnotherBean beanOne) {
        this.beanOne = beanOne;
    }

    public void setBeanTwo(YetAnotherBean beanTwo) {
        this.beanTwo = beanTwo;
    }

    public void setIntegerProperty(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "ExampleBeanBySet{" +
                "beanOne=" + beanOne +
                ", beanTwo=" + beanTwo +
                ", i=" + i +
                '}';
    }
}