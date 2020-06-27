package com.dxc.presentation;
import java.util.List;
import java.util.Scanner;

import com.dxc.pojo.Book;
import com.dxc.servicelayer.IUserService;
import com.dxc.servicelayer.UserServiceImpl;


public class UserTest 
{

	
	public void userOperation(String name)
	{
		String uName=name;
		
		Scanner sc=new Scanner(System.in);
		LibraryTest lb=new LibraryTest();
		IUserService ius=new UserServiceImpl();
		int uId=ius.getUserId(uName);
		while(true)
		{
			System.out.println("1.Issue books from library");
			System.out.println("2.Get book list of library");
			System.out.println("3.Get list of books by particular author");
			System.out.println("4.Check available balance in library account");
			
			System.out.println("5.Exit");
			
			System.out.println("Enter your choice...");
			int choice=sc.nextInt();
			
			switch (choice)
			{
			case 1:	
				
				
				System.out.println("Enter book id");
				int bId=sc.nextInt();
				System.out.println("Enter no of day Book you want");
				int day=sc.nextInt();
				if(day<=15)
				{
					double balance=day*5;
					ius.issueBook(uId,bId,day,balance);
				}
				else
				{
					System.out.println("Only fifteen days are allowed!!!!...");
				}
				
				break;
				
			case 2:
				List<Book> ls=ius.getBookList();
				System.out.println();
				for(Book bj:ls)
				{
					bj.display();
				}
				System.out.println();
				break;
				
			case 3:
				System.out.println("Enter Author name");
				List<Book> ls1=ius.getBookListOfParticularAuthor(sc.next());
				System.out.println();
				for(Book bj:ls1)
				{
					bj.display();
				}
				System.out.println();
				break;
				
			case 4:
				double balance=ius.getBalance(uId);
				System.out.println("\nbalance: "+balance+"\n");
				break;
			
			case 7:
				ius.closeConnection();
				System.exit(0);
				break;
				
			default:
				System.out.println("Wrong!! choose correct choice");
				break;
			}
		}
	}
}
