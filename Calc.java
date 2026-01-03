package conditionalPrgm;
import java.util.Scanner;
public class Calc
{
	public static void main(String[] args) 
	{
Scanner input=new Scanner(System.in);
int choice;
while(true)
	{
	System.out.println("Enter your choice : ");
	System.out.println("1.Addition");
	System.out.println("2.Subtraction");
	System.out.println("3.Multiplication");
	System.out.println("4.Division");
	System.out.println("5.Modulo");
    choice = input.nextInt();
    if(choice<=5)
    {
    	System.out.println("Enter n1 : ");
    	int n1=input.nextInt();
    	System.out.println("Enter n2 : ");
    	int n2=input.nextInt();
    	switch(choice)
    	{
    	case 1:
    		System.out.println("Addition Results : ");
    		System.out.println(n1+n2);
    		break;
    	case 2:
    		System.out.println("Subtraction Results : ");
    		System.out.println(n1-n2);
    		break;
    	case 3:
    		System.out.println("Multiplication Results : ");
    		System.out.println(n1*n2);
    		break;
    	case 4:
    		System.out.println("Division Results : ");
    		System.out.println(n1/n2);
    		break;
    	case 5:
    		System.out.println("Modulo Results : ");
          if(n2==0)
          {
            System.out.println("Error can't perform modulo ! ");
          }
          else
          {
    		System.out.println(n1%n2);
          }
    		break;
    	}
    }
    else
     {
    	System.out.println("Invalid choice ! Exiting...");
     }
  	}
	}
}
