package com.dxc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.dao.IAdminDao;
import com.dxc.pojo.Book;
import com.dxc.pojo.User;

public class AdminDaoImpl implements IAdminDao{

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
	public boolean passwordCheck(String name,String password) 
	{
		boolean b=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from admin");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(name.equals(rs.getString(2))&&password.equals(rs.getString(3)))
					b=true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void closeConn()
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
	public void addUser(int id, String name, String password, double balance) {
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into user values(?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setDouble(4, balance);
			pstmt.execute();
			System.out.println("\nSuccessfully One user added\n");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBook(int id, String bName, String author, int qnt) {
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into book values(?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, bName);
			pstmt.setString(3, author);
			pstmt.setInt(4, qnt);
			pstmt.execute();
			System.out.println("\nSuccessfully One book added\n");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> getUserBookList(int id) {
		int bookId=0;
		List<Book> bpLs=new ArrayList<Book>();
			try 
			{
				PreparedStatement pstmt=conn.prepareStatement("select * from issuebook where uid=?");
				pstmt.setInt(1,id);
				System.out.println("****************");
				ResultSet rs=pstmt.executeQuery();
				System.out.println("---------------------");
				while(rs.next())
				{
	
                  System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getDouble(4));
				}
				pstmt.close();
				Statement stmt2=conn.createStatement();
				ResultSet rs1=stmt2.executeQuery("select * from book");
				while(rs1.next())
				{
					if(bookId==rs1.getInt(1))
					{
						Book bp=new Book(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getInt(4));
						bpLs.add(bp);
					}
				}
				stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bpLs;
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
	public double getUserBalance(int id) 
	{
		double balance=0;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
					balance=rs.getDouble(4);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}
	
	

	@Override
	public List<Book> getTotalBookList() {
		
		List<Book> ls=new ArrayList<Book>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from book");
			while(rs.next())
			{
				Book book=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				ls.add(book);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public void deleteUser(int id) {
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("delete from user where id=?");
			pstmt.setInt(1, id);
			pstmt.execute();
			pstmt.close();
			System.out.println("\nsuccessfully user deleted from database...\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getUserList() {
		List<User> ls=new ArrayList<User>();
		try {
			
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				User us=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
				ls.add(us);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
}
