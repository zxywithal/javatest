package org.zxy.DependencyInjection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ComplexObject {
    private Properties adminEmails;
    private List<Object> someList;
    private Map<String, Object> someMap;
    private Set<Object> someSet;

    public void setAdminEmails(Properties adminEmails) {
        this.adminEmails = adminEmails;
    }

    public void setSomeList(List<Object> someList) {
        this.someList = someList;
    }

    public void setSomeMap(Map<String, Object> someMap) {
        this.someMap = someMap;
    }

    public void setSomeSet(Set<Object> someSet) {
        this.someSet = someSet;
    }

    @Override
    public String toString() {
        return "ComplexObject{" +
                "adminEmails=" + adminEmails +
                ", someList=" + someList +
                ", someMap=" + someMap +
                ", someSet=" + someSet +
                '}';
    }
}
