

import java.util.Scanner;

public class kakao1 {
   public static void main(String[] args) {
      int n = 0;
      System.out.println("n : ");
      Scanner s = new Scanner(System.in);
      n = s.nextInt();
      if(n<=0) {
         System.out.println("0보다 변수가 작을수없습니다.");
         System.exit(0);
      }else if(n>16) {
         System.out.println("16보다 변수가 클수없습니다.");
         System.exit(0);
      }  
      int[] arr1 = new int[n];
      int[] arr2 = new int[n];
      System.out.println("arr1 : ");
      for(int i=0;i<n;i++) {
         arr1[i] = s.nextInt();
         if(arr1[i]<0) {
       	  System.out.println("값이 0보다 작을수없습니다.");
       	  System.exit(0);
         }else if(arr1[i]>Math.pow(2, n)) {
        	 System.out.println("값이 2^n-1을 넘을수없습니다.");
        	 System.exit(0);
         }
      }
      System.out.println("arr2 : ");
      for(int i=0;i<n;i++) {
         arr2[i] = s.nextInt();
         if(arr2[i]<0) {
          	  System.out.println("값이 0보다 작을수없습니다.");
          	  System.exit(0);
            }else if(arr2[i]>Math.pow(2, n)) {
           	 System.out.println("값이 2^n-1을 넘을수없습니다.");
           	 System.exit(0);
            }
      }    
      String byte1;
      String byte2;
      String[] result = new String[n];
         for(int j=0;j<n;j++) {
            result[j]="";
            byte1 = Integer.toBinaryString(arr1[j]);
            byte2 = Integer.toBinaryString(arr2[j]);
            if(byte1.length()>byte2.length()) {
               int ad = byte1.length()-byte2.length();
               for(int x=0;x<ad;x++) {
                  byte2 = "0" + byte2;
               }
            }else if(byte1.length()<byte2.length()) {
               int ad = byte2.length()-byte1.length();
               for(int x=0;x<ad;x++) {
                  byte1 = "0" + byte1;
               }
            }
            for(int i=0;i<byte1.length();i++) {
               if(byte1.charAt(i)=='0' && byte2.charAt(i)=='0') {
                  result[j] = result[j] + "0";
               }else if(byte1.charAt(i)=='0' && byte2.charAt(i)=='1') {
                  result[j] = result[j] + "1";
               }else if(byte1.charAt(i)=='1' && byte2.charAt(i)=='0') {
                  result[j] = result[j] + "1";
               }else if(byte1.charAt(i)=='1' && byte2.charAt(i)=='1') {
                  result[j] = result[j] + "1";
               }
            }
         }
         for(int z=0;z<n;z++) {
            result[z] = result[z].replace("0","B");
            result[z] = result[z].replace("1","#");
         }
         for(int i=0;i<n;i++) {
        	 System.out.println("result["+i+"] : " + result[i]);
         }
   	}
}