package SubClasses;

import Classes.Character;
import Implementations.Healer;

public class Magnus extends Character {
    public Healer healer;
    public Magnus(String nom) {
        super(nom, "Warrior", "Peut soigner ses alliés", 500, 500);
        healer = new Healer(this);
    }
}
