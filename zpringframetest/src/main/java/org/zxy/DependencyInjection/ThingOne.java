package org.zxy.DependencyInjection;

public class ThingOne {
    private ThingThree thingThree;
    private ThingTwo thingTwo;
    public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
        this.thingThree = thingThree;
        this.thingTwo = thingTwo;
        System.out.println(this.thingThree);
        System.out.println(this.thingTwo);
    }
}