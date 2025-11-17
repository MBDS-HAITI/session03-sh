package Classes;

import SubClasses.Colossus;
import SubClasses.Dwarf;
import SubClasses.Magnus;
import SubClasses.Warrior;

import java.util.ArrayList;

public class Player {

    private String name;
    private final ArrayList<Character> characters = new ArrayList<>();
    private int counter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        for (int i=1; i<characters.size(); i++){
            this.characters.add(characters.get(i));
        }
    }
    public int getCounter() {
        return counter;
    }

    public void setCounter(int compteur) {
        this.counter = compteur;
    }

    public Player(String nom, ArrayList<String> charactersName) {
        this.name = nom;
        this.counter = 0;
        for (String s : charactersName) {
            switch (s) {
                case "Warrior":
                    this.characters.add(new Warrior(name+"-"+s));
                    break;
                case "Magnus":
                    this.characters.add(new Magnus(name+"-"+s));
                    break;
                case "Colossus":
                    this.characters.add(new Colossus(name+"-"+s));
                    break;
                case "Dwarf":
                    this.characters.add(new Dwarf(name+"-"+s));
                    break;
                default:
                    throw new IllegalArgumentException("Personnage mal onfigué!");

            }
            for(Character c : this.characters){
                this.counter = this.getCounter()+ c.getPoint();
            }
        }
    }

    public ArrayList<Character> updateCharacters(Character character){
        if(this.characters.contains(character)){
            this.characters.remove(character);
        }
        else
            characters.add(character);
        return characters;
    }
    public Player (String name)
    {
        this.name = name;
        this.counter = 0;
    }
}
