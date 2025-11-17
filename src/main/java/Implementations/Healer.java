package Implementations;
import Classes.Character;
import SubClasses.*;
import Contracts.Action;

public class Healer implements Action {
    private Magnus magnus;
    public Healer(Magnus magnus) {
        this.magnus = magnus;
    }
    @Override
    public int facing(Character character1,  Character character2) {
        return character2.getWaepon().getPower();

    }
}
