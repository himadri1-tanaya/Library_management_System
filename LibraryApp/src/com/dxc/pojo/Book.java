package com.dxc.pojo;

public class Book
{
private int id;
private String bookName;
private String authorName;
private int quantity;

public Book() {
}

public Book(int id, String bookName, String authorName, int quantity) {
	super();
	this.id = id;
	this.bookName = bookName;
	this.authorName = authorName;
	this.quantity = quantity;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getBookName() {
	return bookName;
}

public void setBookName(String bookName) {
	this.bookName = bookName;
}

public String getAuthorName() {
	return authorName;
}

public void setAuthorName(String authorName) {
	this.authorName = authorName;
}



public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public void display()
{
	System.out.println(id+" "+bookName+" "+ authorName+" "+ quantity);
}
}