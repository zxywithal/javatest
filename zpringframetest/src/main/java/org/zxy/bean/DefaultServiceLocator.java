package org.zxy.bean;

public class DefaultServiceLocator {

    private static ClientService clientService = new ClientService();
    private OneBean oneBean = OneBean.getInstance();


    public ClientService createClientServiceInstance() {
        return clientService;
    }

    public OneBean createOneBeanInstance() {
        return oneBean;
    }
}