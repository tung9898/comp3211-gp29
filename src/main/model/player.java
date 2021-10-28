package model;
public class player {
    public String name;
    public int id;
    
    public player(String _name, int _id){
        this.name = _name;
        this.id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "player [id=" + id + ", name=" + name + "]";
    }
}
