package cn.augusto.network;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AllLocalIp {

  public static void main(String[] argv) {
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      List<InetAddress> addrs = new ArrayList<InetAddress>();
      while(interfaces.hasMoreElements()) {
        NetworkInterface nif = interfaces.nextElement();
        nif.getInterfaceAddresses().forEach( addr -> {
          addrs.add(addr.getAddress());
        });
      }

      List<Inet4Address> addr4s = new ArrayList<>();
      List<Inet6Address> addr6s = new ArrayList<>();
      addrs.stream().forEach(item -> {
        if (item instanceof  Inet4Address){
          addr4s.add((Inet4Address)item);
        }
        else{
          addr6s.add((Inet6Address)item);
        }
      });

      addrs.stream().forEach(addr -> {
        System.out.println(addr.toString());
      });
      System.out.println("--------------------------");
      addr4s.stream().parallel().forEach(addr -> {
        System.out.println(addr.getHostAddress() + " / " + addr.getCanonicalHostName());
      });
      System.out.println("--------------------------");
      addr6s.stream().parallel().forEach(addr -> {
        System.out.println(addr.getHostAddress() + " / " + addr.getCanonicalHostName());
      });

    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
