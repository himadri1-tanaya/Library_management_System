package com.dxc.servicelayer;


import java.util.List;
import com.dxc.pojo.Book;

public interface IUserService {

	boolean PasswordCheck(String name, String password);

	public List<Book> getBookList();

	public List<Book> getBookListOfParticularAuthor(String autherName);

	public void issueBook(int uId, int bId, int day,double balance);

	public double getBalance(int userId);

	public void closeConnection();

	public int getUserId(String name);

	void returnBook(int uId, int bId);

	List<Book> getIssuedBook(int uId);

}
