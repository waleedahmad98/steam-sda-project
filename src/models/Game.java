package models;

public class Game {
    public Game() {}

    public Game(int id, String name, String iconLink) {
        setId(id);
        setName(name);
        setLink(iconLink);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return iconLink;
    }

    public void setLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getExecPath(){
        return executablePath;
    }

    public void setExecPath(String executablePath){
        this.executablePath = executablePath;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    private int id;
    private String name;
    private String iconLink;
    private String executablePath;
}
