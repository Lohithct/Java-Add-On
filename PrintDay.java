package conditionalPrgm;
import java.util.Scanner;
public class PrintDay 
{
	public static void main(String[] args) 
	{
Scanner input = new Scanner(System.in);
System.out.println("Enter day number : ");
int dayno = input.nextInt();
switch(dayno)
{
case 1 :
	System.out.println("Sunday");
	break;
case 2 :
	System.out.println("Monday");
	break;
case 3 :
	System.out.println("Tuesday");
	break;
case 4 :
	System.out.println("Wednesday");
	break;
case 5 :
	System.out.println("Thursady");
	break;
case 6 :
	System.out.println("Friday");
	break;
case 7 :
	System.out.println("Saturday");
	break;
default :
	System.out.println("Invalid input");
	break;
  }
 }
}
