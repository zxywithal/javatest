import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.zxy.bean.ClientService;
import org.zxy.bean.OneBean;

import java.math.BigDecimal;

public class TestStart {
    public static void main(String[] args) {
// create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aplicationContext.xml");
//        GenericApplicationContext applicationContext = new GenericApplicationContext();
//        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("classpath:aplicationContext.xml");
// retrieve configured instance
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader "+systemClassLoader);
        System.out.println(context.getBean("oneBean", OneBean.class));
        ClientService clientService = context.getBean("clientService", ClientService.class);
        System.out.println(clientService);
        System.out.println(context.getBean("exampleBean"));
        System.out.println(context.getBean("exampleBeanBySet"));
        System.out.println(context.getBean("exampleBeanByCons"));
        System.out.println(context.getBean("complexObject"));
        System.out.println(context.getBean("exampleBeanDependOn"));
    }
}
