package cn.augusto.network;

import java.net.Socket;
import java.util.Scanner;

public class SocketTest {


  public static void main(String[] argv){
    try{
      Socket sock = new Socket("time-a.nist.gov", 13);
      Scanner in = new Scanner(sock.getInputStream(), "UTF-8");
      while(in.hasNextLine()){
        String line = in.nextLine();
        System.out.println(line);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
