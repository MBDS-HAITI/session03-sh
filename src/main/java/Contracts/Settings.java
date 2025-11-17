package Contracts;

import Classes.Player;
import Classes.Character;

import java.util.ArrayList;

public interface Settings {
    public ArrayList<Character> getCharacters();
    public ArrayList<Character> setCharacters();
    public ArrayList<String> identifyPlayer(String joueur1, String joueur2);
    public ArrayList<Player> createTeams(ArrayList<String> personnages1, ArrayList<String> personnages2);



}
