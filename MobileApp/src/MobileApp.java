import java.util.ArrayList;
import java.util.Scanner;

class MobilePhone
{
	private int phid;
	private String  phbrand;
	private int cost;
	
	public MobilePhone()
	{
		
	}

	public MobilePhone(int phid, String phbrand, int cost) 
	{
		super();
		this.phid = phid;
		this.phbrand = phbrand;
		this.cost = cost;
	}
	public int getPhId()
	{
		return phid;
	}
	public void accept()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter phid");
		phid=sc.nextInt();
		System.out.println("Enter the phone name");
		phbrand=sc.next();
		System.out.println("enter the updated cost");
		cost=sc.nextInt();
		
		
	}
	public void updateMobile()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter updated name");
		phbrand=sc.next();
		System.out.println("enter the updated cost");
		cost=sc.nextInt();
	}
	public void display()
	{
		System.out.println(phid+" "+phbrand+" "+cost);
	}
}



	