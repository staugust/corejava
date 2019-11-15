package cn.augusto.euler;

import java.math.BigInteger;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

public class Euler207 {

  public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

    Map<Integer, BigInteger> mp =new HashMap<Integer, BigInteger>();
    BigInteger base = new BigInteger("2");
    BigInteger pn = base.multiply(base);
    for(int n = 2; n < 64; n++){
      pn = pn.multiply(base);
      mp.put(n, pn.subtract(base));
    }
    Scanner scan = new Scanner(System.in);
    int T = scan.nextInt();
    while(T > 0){
      long al, bl;
      al = scan.nextLong();
      bl = scan.nextLong();

      if ( ((al << 1) - bl) > 0) {
        System.out.println("6");
      }
      else if(al * 5 - bl - bl >0){
        System.out.println("30");
      }
      else if( al * 3 - bl > 0){
        //n = 2
        System.out.println("42");
      }
      else {
        try {
          Class<?> c = Class.forName("java.math.MutableBigInteger");
          Constructor<?> con = c.getDeclaredConstructor(long.class);
          con.setAccessible(true);
          Object i = con.newInstance(1);
          Method m = c.getDeclaredMethod("mul", new Class[] { long.class, c });
          m.setAccessible(true);
        }catch (Exception e){
          e.printStackTrace();
        }

        BigInteger a = new BigInteger("" + al);
        BigInteger b = new BigInteger(""+ bl);
        int n = 3;
        while(n < 64){
          if( a.multiply(mp.get(n)).compareTo(b.multiply(new BigInteger(""+n))) > 0){
            break;
          }
          n++;
        }
        assert n < 64;
        BigInteger at = b.multiply(new BigInteger("" + n)).divide(a).add(BigInteger.ONE);
        System.out.println(at.multiply(at.add(BigInteger.ONE)));
      }
      T--;
    }
  }
}
