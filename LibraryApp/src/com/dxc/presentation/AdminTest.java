package com.dxc.presentation;

import java.util.List;
import java.util.Scanner;

import com.dxc.pojo.Book;
import com.dxc.pojo.User;
import com.dxc.servicelayer.AdminServiceImpl;
import com.dxc.servicelayer.IAdminService;


public class AdminTest 
{
	Scanner sc=new Scanner(System.in);
	
	IAdminService ias=new AdminServiceImpl();
	
	public void adminOperation()
	{
		
		while(true) 
		{
			System.out.println("1.Add user");
			System.out.println("2.Add book");
			System.out.println("3.get list of book issued by particular user");
			System.out.println("4.get balance amount of particular user");
			System.out.println("5.get total book list");
			System.out.println("6.get total user list");
			System.out.println("7.Delete user from library");
			System.out.println("8.exit");
			
			System.out.println("\nChoose your choice...");
			int choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter user id,name,password,balance");
				//double balance=1000.0;
				//ias.addUser(sc.nextInt(),sc.next(),sc.next(),sc.nextDouble());
				ias.addUser(sc.nextInt(), sc.next(), sc.next(),sc.nextDouble());
				break;
			case 2:
				System.out.println("Enter book id,book name,author name & quantity");
				ias.addBook(sc.nextInt(), sc.next(), sc.next(), sc.nextInt());
				break;
			case 3:
				System.out.println("Enter user id");
				List<Book> ls=ias.getUserBookList(sc.nextInt());
				System.out.println();
				for(Book bp:ls)
				{
					bp.display();
				}
				System.out.println();
				break;
			case 4:
				System.out.println("Enter user id");
				double balance1=ias.getUserBalance(sc.nextInt());
				System.out.println("\nbalance : "+balance1+"\n");
				break;
			case 5:
				List<Book> ls2=ias.getTotalBookList();
				System.out.println();
				for(Book bp:ls2)
				{
					bp.display();
				}
				System.out.println();
				break;
			case 6:
				List<User> userLs=ias.getUserList();
				System.out.println();
				for(User up:userLs)
				{
					up.display();
				}
				System.out.println();
				break;
			case 7:
				System.out.println("Enter user id");
				ias.deleteUser(sc.nextInt());
				break;
			case 8:
				ias.closeConnection();
				System.exit(0);
				break;
			default:
				System.out.println("Wrong!! Enter correct choice..");
				break;
			}
		}
	}
}
