package models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Game {
    public Game() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return icon;
    }

    public void setLink(String iconLink) {
        this.icon = iconLink;
    }

    public String getExecPath(){
        return executablePath;
    }

    public void setExecPath(String executablePath){
        this.executablePath = executablePath;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }


    @BsonId()
    private ObjectId id;
    private String name;
    private String icon;
    private double price;
    private String executablePath;

}
