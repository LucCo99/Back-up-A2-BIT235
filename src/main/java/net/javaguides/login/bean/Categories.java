package net.javaguides.login.bean;

public class Categories {
	protected int id;
    protected String title;
    protected String category;

    public Categories() {}

    public Categories(int id, String title,String category) {
        this.id = id;
        this.title = title;
        this.category = category;

    }

    public Categories(int id,String category) {
        this.id = id;
        this.category = category;

    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

  

   
}
