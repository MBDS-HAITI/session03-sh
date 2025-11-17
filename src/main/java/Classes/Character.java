package Classes;

import java.util.ArrayList;

public class Character {
    private String name;
    private String type;
    private String Description;
    private int point;
    private Waepon waepon;
    static ArrayList<String> characters = new ArrayList<String>();
    static ArrayList<Character> characterss = new ArrayList<Character>();
    public String getName() {
        return name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Waepon getWaepon() {
        return waepon;
    }

    public void setWaepon(Waepon waepon) {
        this.waepon = waepon;
    }

    public static ArrayList<String> getCharacters() {
        return characters;
    }

    public static void setCharacters(ArrayList<String> characterNames) {
        characters = characterNames;
    }

    public int action(){
        return this.waepon.getPower();
    }

    public Character(String name, String type, String Description, int point, int armPower) {
        this.name = name;
        this.type = type;
        this.Description = Description;
        this.point = point;
        this.waepon = new Waepon(armPower);

    }
}
