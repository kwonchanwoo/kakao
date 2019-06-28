import java.util.Scanner;
import java.util.regex.*;
public class kakao2 {
   public static void main(String[] args) {
	   kakao2 re = new kakao2();
      Scanner s = new Scanner(System.in);
      String str="";
      System.out.println("점수를 입력해주세요.");
      str = s.next();
      System.out.println("총 점수 합은" + re.result(str) +"입니다.");
   }
   int result(String str) {
      Pattern ptn = Pattern.compile("[0-9][0-9].[*|#]|[0-9][0-9].|[0-9].[*|#]|[0-9]."); 
      Matcher matcher = ptn.matcher(str);
      String[] jumsu = new String[3];
      int[] sub_result = new int[3];
      int count=0;
      int anarisk=2;
      while(matcher.find()){ 
         jumsu[count] = matcher.group(0); //인덱스랑 햇갈리지말자. matcher 쓸때 group값 뒤엔 항상 0적자. ㅋㅋ
         count++;
      } 
      for(int i=0;i<=2;i++) {
         for(int j=0;j<jumsu[i].length();j++) {
            if(jumsu[i].charAt(0)=='1' && jumsu[i].charAt(1)=='0') { //숫자 자리수가 두자리수인경우 
               sub_result[i] = 10;
               if(j==2) {
                  int t2 = 0;
                  if(jumsu[i].charAt(2)=='S') {
                      t2 =1;
                  }else if(jumsu[i].charAt(2)=='D') {
                      t2 =2;
                  }else if(jumsu[i].charAt(2)=='T') {
                      t2 =3;
                  }
                  sub_result[i] =  (int) Math.pow(sub_result[i],t2);
               }         
               if(jumsu[i].charAt(j)=='*') {
                  sub_result[i] = sub_result[i]*anarisk;
                  if(i==1) {
                     sub_result[0] = sub_result[0]*2;
                  }else if(i==2) {
                     sub_result[1] = sub_result[1]*2;
                  }
               }else if(jumsu[i].charAt(j)=='#') {
                  sub_result[i] = sub_result[i]*-1;
               }
            }else {
               if(j==0) {
                  String t = String.valueOf(jumsu[i].charAt(0));
                  sub_result[i] = Integer.parseInt(t);
               }else if(j==1) {
                  int t2 = 0;
                  if(jumsu[i].charAt(1)=='S') {
                      t2 =1;
                  }else if(jumsu[i].charAt(1)=='D') {
                      t2 =2;
                  }else if(jumsu[i].charAt(1)=='T') {
                      t2 =3;
                  }
                  sub_result[i] =  (int) Math.pow(sub_result[i],t2);
               }
               if(jumsu[i].charAt(j)=='*') {
                  sub_result[i] = sub_result[i]*anarisk;    
                  if(i==1) {
                     sub_result[0] = sub_result[0]*2;
                  }else if(i==2) {
                     sub_result[1] = sub_result[1]*2;
                  }
               }else if(jumsu[i].charAt(j)=='#') {
                  sub_result[i] = sub_result[i]*-1;
               }
            }
         }
      }
      return sub_result[0]+sub_result[1]+sub_result[2];   
   }
}