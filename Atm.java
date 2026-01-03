package conditionalPrgm;
import java.util.Scanner;
public class Atm 
{
	public static void main(String[] args) 
	{
Scanner input=new Scanner(System.in);
System.out.println("Enter pin : ");
int pin = input.nextInt();
int correctpin = 0000;
int balance =15000;
if(pin==correctpin)
{
	System.out.println("Enter amount : ");
	int withdraw=input.nextInt();
	if(withdraw<=balance)
	{
	System.out.println("Withdrawn successfully ...");	
	}
	else
	{
System.out.println("Insufficient amount ! try again.");
	}
}
else
{
	System.out.println("Invalid pin number ! try again.");
}
	}
}
