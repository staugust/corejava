package cn.augusto.network;

import java.net.Socket;
import java.util.Scanner;

public class Telnet {
  public static void main(String[] argv) {
    try {
      Socket sock = new Socket("localhost", 62233);
      System.out.println(sock.isConnected());
      Scanner in = new Scanner(System.in, "UTF-8");
      Scanner reader = new Scanner(sock.getInputStream(), "UTF-8");
      Thread th = new Thread(() -> {
        while (!sock.isInputShutdown() && reader.hasNext()) {
          System.out.println(reader.nextLine());
        }
      });
      th.start();
      while (!sock.isOutputShutdown() && in.hasNext()) {
        String str = in.nextLine();
        str += "\n";
        byte[] arr = str.getBytes("UTF-8");
        sock.getOutputStream().write(arr);
        sock.getOutputStream().flush();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
