package cn.augusto.network;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {

  public static void main(String[] argv){
    try {
      ServerSocket server = new ServerSocket();
      server.bind(new InetSocketAddress(Inet4Address.getByName("localhost"), 62233));
      Scanner in = new Scanner(System.in);
      while(true){
        Socket sock = server.accept();
        Thread th = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              Scanner s = new Scanner(sock.getInputStream(), "UTF-8");
              boolean done = false;
              while(!done && s.hasNext()){
                String line = s.nextLine();
                System.out.println(sock.getRemoteSocketAddress().toString() + " -> " + line);
                if(line.equals("bye"))
                  done = true;
                sock.getOutputStream().write(("-> " + line + "\n").getBytes());
                sock.getOutputStream().flush();
              }
            }catch (Exception e){
              e.printStackTrace();
            }
            System.out.println("close sock -> " + sock.getRemoteSocketAddress().toString());
            try {
              sock.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
        th.start();
      }
//      server.close();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
