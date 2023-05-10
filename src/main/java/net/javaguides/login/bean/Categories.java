package net.javaguides.login.bean;

public class Categories {
	protected int id;
    protected String title;

    public Categories() {}

    public Categories(int id, String title) {
        this.id = id;
        this.title = title;

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

  

   
}
