package com.dxc.pojo;

public class IssuedBook 
{
	private int uId;
	private int bookId;
	private int noOfDayTaken;
	private int noOfBookTaken;
	public IssuedBook()
	{
		
	}
	public IssuedBook(int uId, int bookId, int noOfDayTaken, int noOfBookTaken) {
		super();
		this.uId = uId;
		this.bookId = bookId;
		this.noOfDayTaken = noOfDayTaken;
		this.noOfBookTaken = noOfBookTaken;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getNoOfDayTaken() {
		return noOfDayTaken;
	}
	public void setNoOfDayTaken(int noOfDayTaken) {
		this.noOfDayTaken = noOfDayTaken;
	}
	public int getNoOfBookTaken() {
		return noOfBookTaken;
	}
	public void setNoOfBookTaken(int noOfBookTaken) {
		this.noOfBookTaken = noOfBookTaken;
	}
	
}
