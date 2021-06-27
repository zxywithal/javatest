package javacore.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        if (args.length>0) {
            InetAddress[] allByName = InetAddress.getAllByName(args[0]);
            for (InetAddress inetAddress : allByName) {
                System.out.println(inetAddress);
            }
        } else {
            System.out.println(InetAddress.getLocalHost());
        }
    }
}
