package net.javaguides.login.bean;

public class Article {
    protected int id;
    protected String title;
    protected String body;
    protected String date;
    protected String category;
    protected int status;
    public Article() {}
    
    public Article(String title, String body, String date) {
    	 this.title = title;
         this.body = body;
         this.date = date;
   	 }
    public Article(String category) {
    	this.category = category;
  	 }

    public Article(int id, String title, String body, String date, int status) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.status = status;

    }
    public Article(int id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
