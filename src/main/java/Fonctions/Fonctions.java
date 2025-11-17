package Fonctions;

import Classes.Character;
import Classes.Player;
import Implementations.setUpGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Fonctions {

    public static void setCharacter() {
        ArrayList<String> characterNames = new ArrayList<>();

        characterNames.add("Warrior");
        characterNames.add("Magnus");
        characterNames.add("Colossus");
        characterNames.add("Dwarf");
        Character.setCharacters(characterNames);
    }

    public static ArrayList<String> getAvailableCharacters(ArrayList<String> characters) {
        ArrayList<String> availableCharacters = new ArrayList<>(Character.getCharacters());
         availableCharacters.removeAll(characters);
         return availableCharacters;
    }
    public static void showCharacters(ArrayList<String> characterNames, String playerName){

        ArrayList<String> availableCharacterNames = getAvailableCharacters(characterNames);
        if(!characterNames.isEmpty()) {
            for (String characterName : characterNames) {
                availableCharacterNames.remove(characterName);
            }
        }
        System.out.println(playerName +", choisis ton "+(characterNames.size() +1) + "e personnage :\n ");
        for (int j = 0; j<availableCharacterNames.size() ; j++) {
            System.out.println(
                    j+1 + "-" + availableCharacterNames.get(j) +".");
        }
    }
    public static void welcomeMessage(){
        System.out.println("Bienvenue dans le battle du siecle. \n Vous allez vous affronter jusqu\'à la mort!!!!!!");
        System.out.println("Merci de vous identifier et de créer votre équipe à tour de rôle. \n Allons avec l'équipe 1");
    }

    public static ArrayList<String> identifyPlayers()
    {
        ArrayList<String> players = new ArrayList<>();
        System.out.println("Qui es-tu joueur 1?");
        String playerName1 = new Scanner(System.in).next();
        players.add(playerName1);
        System.out.println("Qui es-tu joueur 2?");
        String playerName2 = new Scanner(System.in).next();
        while (playerName1.equals(playerName2)){

            System.out.println("Merci de fournir un nom différent de celui de ton adversaire!");
             playerName2 = new Scanner(System.in).next();
        }
        players.add(playerName2);
        return players;
    }

    public static void indication(String player1, String player2){

        System.out.println("Super "+ player1 + " et " + player2 +". Vous connaissez les règles du jeu?\n Tapez:" +
                "\"non\", si vous voulez voir les intructions. \n" +
                "\"oui\" pour passer directement à la constitution de vos équipes:");
        String connaitLeJeu = new Scanner(System.in).next();
        if(connaitLeJeu.equals("oui")){
            System.out.println("Great!\n");
        }
        else
            System.out.println("la flemme d'ecrire tout ca. On y reviendra. C\'est parti!!!!!!\n");

    }

    public static void describeTeam(Player player)
    {
        System.out.println("\n L\'équipe de " + player.getName() + " est ainsi constitué de :\n");
        for (int i = 0; i< player.getCharacters().size(); i++) {
            System.out.print((i+1)+"-"+player.getCharacters().get(i).getName()+"," +
                    ""+ player.getCharacters().get(i).getDescription()+ ", " +
                    "avec "+player.getCharacters().get(i).getPoint() +" points de vie, et " +
                    "une arme de puissance "+player.getCharacters().get(i).getWaepon().getPower() +"\n");
        }
    }

    public static void calculatePoints(setUpGame newSetUpGame, Character activeCharacter, Character reactiveCharacter)
    {
        int gain = activeCharacter.getPoint() - reactiveCharacter.getPoint();


        if(newSetUpGame.getPlayer1().getCharacters().contains(activeCharacter)){
            int indexOf1 = newSetUpGame.getPlayer1().getCharacters().indexOf(activeCharacter);
            int indexOf2 = newSetUpGame.getPlayer2().getCharacters().indexOf(reactiveCharacter);
            newSetUpGame.getPlayer1().getCharacters().get(indexOf1).setPoint(gain);
            newSetUpGame.getPlayer2().getCharacters().get(indexOf2).setPoint(-gain);
            newSetUpGame.getPlayer1().setCounter(gain);
            newSetUpGame.getPlayer2().setCounter(-gain);
        }
        else{

            int indexOf2 = newSetUpGame.getPlayer2().getCharacters().indexOf(activeCharacter);
            int indexOf1 = newSetUpGame.getPlayer1().getCharacters().indexOf(reactiveCharacter);
            newSetUpGame.getPlayer2().getCharacters().get(indexOf2).setPoint(gain);
            newSetUpGame.getPlayer1().getCharacters().get(indexOf1).setPoint(-gain);
            newSetUpGame.getPlayer2().setCounter(gain);
            newSetUpGame.getPlayer1().setCounter(-gain);
        }
    }
}
