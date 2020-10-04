import java.util.ArrayList;
import java.util.Scanner;

public class Test 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		MobilePhone m;
		int id,index;
		ArrayList<MobilePhone>list=new ArrayList<>();
		while(true)
		{
			System.out.println("1. add record");
			System.out.println("2. update record");
			System.out.println("3. remove record");
			System.out.println("4. display record");
			int choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				    m=new MobilePhone();
				    m.accept();
				    list.add(m);
				    break;
			case 2:
				   System.out.println("Enter the id of mobile to be updated");
				   id=sc.nextInt();
				   for(MobilePhone phone:list)
				   {
					   if(id==phone.getPhId())
					   {
						   phone.updateMobile();
					   }
				   }
				  break;
			case 3:
				    index=0;
				    System.out.println("Enter id of mobile to be updated");
				    id=sc.nextInt();
				    for(MobilePhone phone:list)
					   {
						   if(id==phone.getPhId())
						   {
							   break;
						   }
						   index++;
					   }
				    list.remove(index);
				    break;
			case 4:
				 for(MobilePhone phone:list)
				   
					   
					   {
						   phone.display();
					   }
					   System.out.println();
					   break;
			}
		}

		}
	}





