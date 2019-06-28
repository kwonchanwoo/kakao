
import java.util.Scanner;

public class kakao3 {
   static int cacheSize =0;
   static String[] cities = new String[100000];
   static int count = 0;
   static int cache_hit = 0;
   static int cache_miss = 0;
   static String[] node;
   static int select=0;  //�������� ����¡ ��ġ��!
   static int header=0; // ���Ҷ� ��ó�������ϴ� ���� ��ġ
   public static void main(String[] args) {
     int check=0;
      Scanner s = new Scanner(System.in);
      int time;
      int a = 0,b;
      System.out.println("cacheSize�� ������ �Ͻðٽ��ϱ�?");
      cacheSize= s.nextInt();
       node = new String[cacheSize];
      while(true) {
         System.out.println("���� �̸��� �Է����ּ���(0�Է½� ����)");
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
         for(int i=0;i<cacheSize;i++) { //�� �ʱ�ȭ ����
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
                   }else {  //����¡��Ʈ�� �߻�����������! 
                      if(a==i) {
                         header=header+2;
                      }
                      if(same_value(cities[i])==true) { //���� ���� ���� ��
                         if(a==i) {
                            header--;
                         }
       // `                 System.out.println("���غ���");
                         System.out.println(cities[insert_value(header,i)]);
                         node[select] = cities[i];
                         cache_miss+=5;
                        
                         lru_print();
                         break;
                      }else {// ���� ���� ������ �ߵ�
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
      System.out.println("�Ѱɸ� �ð� : " + time);
   }
   static boolean paging_fault() {  //������ ��� ����ִ��� Ȯ���ϴ� �޼ҵ�
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
         if(check==0) { //���� ���� ���ϰ� ���� ���� �ȳ��´ٸ� break�ɰ� �װ��� �Ѱ��ش�.
            result = i;
            for(int z=0;z<cacheSize;z++) {
               
               if(node[z].equals(cities[result])) {
                  select = z; //����¡ ��ġ�� �־���    
                  header++;
                  break;
               }
            }
          
         }else {    
            check=0;  //�� �ѹ� �� ���ϰ� �ٽ� ���ֱ����ؼ� check�� 0�� �Ǿ���Ѵٰ� ������     
             //Ȥ�� ���� ������ �ִٸ� header �� ++
           
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