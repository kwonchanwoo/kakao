
import java.util.Scanner;

public class kakao3 {
   static int cacheSize =0;
   static String[] cities = new String[100000];
   static int count = 0;
   static int cache_hit = 0;
   static int cache_miss = 0;
   static String[] node;
   static int select=0;  //넣으려는 페이징 위치값!
   static int header=0; // 비교할때 맨처음시작하는 숫자 위치
   public static void main(String[] args) {
     int check=0;
      Scanner s = new Scanner(System.in);
      int time;
      int a = 0,b;
      System.out.println("cacheSize를 몇으로 하시겟습니까?");
      cacheSize= s.nextInt();
       node = new String[cacheSize];
      while(true) {
         System.out.println("도시 이름을 입력해주세요(0입력시 종료)");
         cities[count] = s.next().toUpperCase().trim();
         if(cities[count].equals("0")) {
            break;
         }
         count++;
      }
      if(cacheSize==0) {
       for(int i=0;i<count;i++) {
          cache_miss +=5;
       }
      }else {
         for(int i=0;i<cacheSize;i++) { //값 초기화 설정
                node[i]="";                 
             }
                   
             for(int i=0;i<count;i++) {
                for(int j=0;j<node.length;j++) {
                   if(paging_fault()==false) {
                      if(node[j].equals("")) {
                         node[j] = cities[i];
                         cache_miss+=5;
                         
                         lru_print();
                         break;
                      }
                   }else {  //페이징폴트가 발생하지않을시! 
                      if(a==i) {
                         header=header+2;
                      }
                      if(same_value(cities[i])==true) { //같은 값이 없을 때
                         if(a==i) {
                            header--;
                         }
       // `                 System.out.println("비교해보자");
                         System.out.println(cities[insert_value(header,i)]);
                         node[select] = cities[i];
                         cache_miss+=5;
                        
                         lru_print();
                         break;
                      }else {// 같은 값이 잇을시 발동
                         cache_hit+=1;  
                         lru_print();
                        a=i+2;
                        
                         break;
                      }
                   }
                }
             }    
      }
      
      
      time = cache_hit+cache_miss;
      
      System.out.println("cache_hit : " + cache_hit);
      System.out.println("cache_miss : " + cache_miss);
      System.out.println("총걸린 시간 : " + time);
   }
   static boolean paging_fault() {  //세값이 모두 들어있는지 확인하는 메소드
      boolean fault=true;
      for(int i=0;i<cacheSize;i++) {
         if(node[i].equals("")) {
            fault=false;
         }
      }
      return fault;
   }
   
   static boolean same_value(String cities) {
      String str;
      str = cities;
      boolean value=true;
      for(int i=0;i<cacheSize;i++) {
         if(str.equals(node[i])) {
            value=false;
         }
         
      }
      
      return value;
   }
   
   static int insert_value(int header_value,int i_value) {
      int first=header_value; int last= i_value;
      int check=0,result=0;
      
      Loop1 :  for(int i=first;i<last;i++) {
         for(int j=first+1;j<last;j++) {
            if(cities[i].equals(cities[j])) {
               check++;
            }
         }
         if(check==0) { //만약 값을 비교하고 같은 값이 안나온다면 break걸고 그값을 넘겨준다.
            result = i;
            for(int z=0;z<cacheSize;z++) {
               
               if(node[z].equals(cities[result])) {
                  select = z; //페이징 위치값 넣어줌    
                  header++;
                  break;
               }
            }
          
         }else {    
            check=0;  //값 한번 다 비교하고 다시 해주기위해선 check가 0이 되어야한다고 생각함     
             //혹시 값이 같은게 있다면 header 에 ++
           
         }
        
      }
      return result;
      
   }
   
   static void lru_print() {
      
//      for(int i=0;i<count;i++) {
//         System.out.print(cities[i]+"\t");
//      }
//      System.out.println();
      System.out.println();
      for(int j=0;j<cacheSize;j++) {
         System.out.print(node[j]+"\t");
      }
      System.out.println("header : " + header);
      System.out.println();
   }

}