package net.javaguides.login.bean;

public class Article {
    protected int id;
    protected String title;
    protected String body;
    protected String date;
    protected String category;

    public Article() {}

    public Article(int id, String title, String body, String date,String category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.category = category;

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
}
