package SubClasses;

import Classes.Character;

public class Warrior extends Character {
    public Warrior(String nom) {
        super(nom, "Warrior", "Attaquant équilibré", 250, 250);
    }

    @Override
    public int action() {
        return 0;
    }


    public int defend(){
        return 0;
    }
}
