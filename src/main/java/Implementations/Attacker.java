package Implementations;

import Classes.Character;
import Contracts.Action;

public class Attacker implements Action {

    @Override
    public int facing( Character character1, Character character2) {
        return character1.getPoint() - character2.getPoint();
    }
}
