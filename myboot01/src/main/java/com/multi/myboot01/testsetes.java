package com.multi.myboot01;

import java.util.Scanner;

public class testsetes {
                                                                                                                                
	   public static void main(String[] args) {                                                                                                        
	                                                                                                                                                  
	      Scanner scan = new Scanner(System.in);                                                                                                      
	      System.out.println("< 0부터 10000까지 중에 있는 숫자를 쓰시오 >");                                                                                        
	                                                                                                                                                  
	      int end = scan.nextInt();                                                                                                                   
	      int total = 0;                                                                                                                              
	      int count = 0;                                                                                                                              
	                        
	      int test = 123 ;
	      int test10 = (test%100)/10;
	      int test1 = test%10;
	      System.out.println(test10+":"+test1);
	      while(count<=end) {                                                                           
	            int t = count/1000;                                                                                                                 
	            int h = (count%1000)/100;                                                                                                                  
	            int ten = (count%100)/10;
	            int one = count%10;
	                                                                                                                                               
	            if(t==3||t==6|t==9) {                                                                                                               
	               total++;
	            }                                                                                                                                   
	                                                                                                                                                
	            if(h==3||h==6||h==9) {                                                                                                              
	               total++;
	            }                                                                                                                                   
	                                                                                                                                                
	            if(ten==3||ten==6||ten==9) {                                                                                                        
	               total++;
	            }   
	            
	            if(one==3||one==6||one==9) {                                                                                                        
		           total++;
		        }                                                                                                                                               
             System.out.println("count : total = " + count + ":" + total);                                                                                                                 
	         count++;                                                                                                                                
	      }                                                                                                                                           
	      System.out.println(total);                                                                                                                  
	   }                                                                                                                                               
}                                                                                                                                                   
	                                                                                                                                                  
	                                                                                                                                                    
	                                                                                                                                                    
	                                                                                                                                                    
	                                                                                                                                                    