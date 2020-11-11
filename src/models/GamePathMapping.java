package models;

import org.bson.types.ObjectId;

public class GamePathMapping {
    private ObjectId id;
    private String path;

    public GamePathMapping(){
        id = null;
        path = null;
    }
    public GamePathMapping(ObjectId id, String path){
        setId(id);
        setPath(path);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
