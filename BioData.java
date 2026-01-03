package conditionalPrgm;
import java.util.Scanner;
public class BioData
{
	public static void main(String[] args) 
	{

Scanner input=new Scanner(System.in);
System.out.println("Enter your name : ");
String name=input.next();
System.out.println("Enter your number : ");
long no=input.nextLong();
System.out.println("Enter your age : ");
int age=input.nextInt();
System.out.println("Enter your address : ");
String address=input.next();

System.out.println(name+" "+no+" "+age+" "+address);

	}
}
