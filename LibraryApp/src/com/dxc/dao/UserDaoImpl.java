package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.dao.IUserDao;
import com.dxc.pojo.Book;


public class UserDaoImpl implements IUserDao{

	private static Connection conn;
	
	
	static
	{
		try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver loaded");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Himadri","root", "PASS");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		System.out.println("connection established");
	}
	
	
	
	
	
	@Override
	public boolean passwordCheck(String name, String password) {
		boolean b1=false;
		try {
			
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			
			while(rs.next())
			{
				if(name.equals(rs.getString(2)) && password.equals(rs.getString(3)))
				{
					b1=true;
					break;
				}
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return b1;
	}
	
	
	
	
	@Override
	public List<Book> getBookList() {
		List<Book> ls=new ArrayList<Book>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from book");
			while(rs.next())
			{
				Book bLst=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				ls.add(bLst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
		
	}
	
	
	
	@Override
	public List<Book> getBookListOfParticularAuthor(String authorName) {
		List<Book> ls=new ArrayList<Book>();
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement("select * from book where author=?");
			pstmt.setString(1, authorName);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				Book bLst=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				ls.add(bLst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	
	@Override
	public void issueBook(int uId, int bId, int day,double balance) {
		double bal=0;
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into issuebook values(?,?,?,?)");
			pstmt.setInt(1, uId);
			pstmt.setInt(2, bId);
			pstmt.setInt(3, day);
			pstmt.setDouble(4, balance);
			pstmt.execute();
			pstmt.close();
			
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				if(uId==rs.getInt(1)) 
				{
					bal=rs.getDouble(4);
					bal=bal-balance;
				}
			}
			stmt.close();
			
			PreparedStatement pstmt1=conn.prepareStatement("update user set balance=? where id=?");
			pstmt1.setDouble(1, bal);
			pstmt1.setInt(2, uId);
			pstmt1.execute();
			pstmt1.close();
			
			System.out.println("\nsuccessfullly issued book from library..\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	
	
	
	

	public double getBalance(int userId) {
		Statement stmt;
		double balance=0.0;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				if(userId==rs.getInt(1))
				{
					balance=rs.getDouble(4);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}

	
	
	
	@Override
	public void closeConnection() 
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}




	@Override
	public int getUserId(String name) {
		int id=0;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				if(name.equals(rs.getString(2)))
				{
					id=rs.getInt(1);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}




	@Override
	public void returnBook(int uId, int bId) {
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("delete from book where id=? AND book=?");
			pstmt.setInt(1, uId);
			pstmt.setInt(2, bId);
			pstmt.execute();
			pstmt.close();
			System.out.println("\nSuccessfully Returned!!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}




	@Override
	public List<Book> getIssuedBook(int uId) {
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select book from  issuebook where userid=?");
			pstmt.setInt(1, uId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				PreparedStatement pstmt1=conn.prepareStatement("select * from book where id=?");
				pstmt1.setInt(1, rs.getInt(1));
				ResultSet rs1=pstmt1.executeQuery();
				while(rs1.next())
				{
					Book bp=new Book(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs.getInt(4));
					ls.add(bp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
}


