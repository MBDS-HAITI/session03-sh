
package org.example;
import Classes.Character;
import Classes.Player;
import Implementations.Attacker;
import Implementations.Healer;
import Implementations.setUpGame;
import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static Fonctions.Fonctions.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void main() {
        ArrayList<Character> Characters = new ArrayList<>();
        welcomeMessage();

        //Naming team
        ArrayList<String> players = identifyPlayers();
        indication(players.get(0), players.get(1));
        setCharacter();

        setUpGame newSetUpGame = new setUpGame(players);

        //Picking up characters
        ArrayList<String> characters1 = new ArrayList<String>();
        ArrayList<String> characters2 = new ArrayList<String>();


        for (int i =0; i<6; i++)
        {
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> characters = new ArrayList<String>();
            String playerName;
            ArrayList<String> characterNames = new ArrayList<String>();
            if(i<3){
                characters.addAll(characters1);
                playerName = players.getFirst();
                characterNames = getAvailableCharacters(characters1);
            }
            else{
                characters.addAll(characters2);
                playerName = players.get(1);
                characterNames = getAvailableCharacters(characters2);
            }
            int characterIndex = 0;
                showCharacters(characters, playerName);

            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Choisissez un personnage (1 à " + characterNames.size() + ") : ");
                    characterIndex = scanner.nextInt();

                    if (characterIndex >= 1 && characterIndex <= characterNames.size()) {
                        validInput = true;
                    } else {
                        System.out.println("Merci de choisir un nombre entre 1 et " + characterNames.size());
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide ! Veuillez entrer un nombre.");
                    scanner.nextLine();
                }
            }
            if(i<3){
                characters1.add(characterNames.get(characterIndex-1));
            }
            else{
                characters2.add(characterNames.get(characterIndex-1));
            }
        }

        newSetUpGame.identifyPlayer(players.get(0), players.get(1));
        ArrayList<Player> playerArrayList = newSetUpGame.createTeams(characters1, characters2);

        describeTeam(newSetUpGame.getPlayer1());
        describeTeam(newSetUpGame.getPlayer2());

        int manche =0;
        int playerIndex = 1;
        Player currentPlayer;
        Player nextPlayer;
        Scanner scanner = new Scanner(System.in);
        Character activeCharacter;
        Character reactiveCharacter;
        Map<Integer, Character> currentCharacters = new HashMap<>();
        ArrayList<Integer> currentIndexesList = new ArrayList<Integer>();

        System.out.println("Count1 = "+ newSetUpGame.getPlayer1().getCounter());
        System.out.println("Count2 = "+ newSetUpGame.getPlayer2().getCounter());

        while (newSetUpGame.getPlayer1().getCounter() > 0 &&  newSetUpGame.getPlayer2().getCounter() > 0) {
            manche = manche + 1;
            System.out.println(newSetUpGame.getPlayer1().getName() + "\t\t\t\t" + newSetUpGame.getPlayer2().getName());
            System.out.println(newSetUpGame.getPlayer1().getCounter() + "\t\t\t" + newSetUpGame.getPlayer2().getCounter());
            currentPlayer = manche % 2 != 0 ? newSetUpGame.getPlayer1() : newSetUpGame.getPlayer2();
            playerIndex = manche % 2 != 0 ? 1 : 2;
            nextPlayer = manche % 2 == 0 ? newSetUpGame.getPlayer1() : newSetUpGame.getPlayer2();
            int action;
            int currentIndex = 0;
            int currentReactiveIndex = 0;
            currentCharacters.clear();
            currentIndexesList.clear();

            ArrayList<String> currentCharacterNames = manche % 2 != 0 ? characters1 : characters2;

            System.out.println("Manche " + manche + ": "+ currentPlayer.getName()+", ton tour.");
            if(currentCharacterNames.contains("Magnus")) {
                System.out.println("1 pour attaquer. \n 2 pour soigner un de tes personnages.");
            }
            else
            {
                System.out.println("1 pour attaquer.");
            }
            action = scanner.nextInt();

            System.out.println(currentPlayer.getName() +", choisis le personnage avec lequel tu veux jouer.");
            for(int i =0; i<currentPlayer.getCharacters().size(); i++){
                if(currentPlayer.getCharacters().get(i).getPoint() > 0){
                    System.out.println( i + "- " + currentPlayer.getCharacters().get(i).getName());
                    currentCharacters.put(i,currentPlayer.getCharacters().get(i));
                    currentIndexesList.add(i);
                }
            };

            currentIndex = scanner.nextInt();
            while(!currentIndexesList.contains(currentIndex)){
                System.out.println("Le chiffre choisi est incorrect.");
                for(int i =1; i<currentPlayer.getCharacters().size();i++){
                    if(currentPlayer.getCharacters().get(i).getPoint() > 0){
                        System.out.println( i + "- " + currentPlayer.getCharacters().get(i-1).getName());
                    }
                };
                currentIndex = scanner.nextInt();
            }
                activeCharacter = currentCharacters.get(currentIndex);

            System.out.println( nextPlayer.getName()+", choisis le personnage que tu veux opposer à "+ activeCharacter.getName() );
            currentCharacters.clear();
            currentIndexesList.clear();

            scanner.nextLine();

            for(int i =0; i<nextPlayer.getCharacters().size(); i++) {
                if (nextPlayer.getCharacters().get(i).getPoint() > 0) {
                    System.out.println(i + "- " + nextPlayer.getCharacters().get(i).getName());
                    currentCharacters.put(i, nextPlayer.getCharacters().get(i));
                    currentIndexesList.add(i);
                }
            };
            currentReactiveIndex = scanner.nextInt();
                while(!currentIndexesList.contains(currentIndex)){
                    System.out.println("Le chiffre choisi est incorrect.");
                    for(int j =1; j<nextPlayer.getCharacters().size();j++){
                        if(nextPlayer.getCharacters().get(j).getPoint() > 0){
                            System.out.println( j + "- " + nextPlayer.getCharacters().get(j-1).getName());
                        }
                    };
                }
            reactiveCharacter = currentCharacters.get(currentReactiveIndex);
            System.out.println(activeCharacter.getName() + "(" + activeCharacter.getPoint()+ ") VS " + reactiveCharacter.getName() + "(" + reactiveCharacter.getPoint()+")" );



            if(action == 1) {
                Attacker attacker = new Attacker();
                int gain = activeCharacter.getPoint() - reactiveCharacter.getPoint();

                if(playerIndex == 1){
                    gain = attacker.facing(newSetUpGame.getPlayer1().getCharacters().get(currentIndex), newSetUpGame.getPlayer2().getCharacters().get(currentReactiveIndex));

                    System.out.println("gain : " + gain);
                    if(gain > 0)
                    {

                        newSetUpGame.getPlayer2().setCounter(newSetUpGame.getPlayer2().getCounter() - newSetUpGame.getPlayer2().getCharacters().get(currentReactiveIndex).getPoint());
                        newSetUpGame.getPlayer2().getCharacters().get(currentReactiveIndex).setPoint(0);
                        newSetUpGame.getPlayer1().setCounter(newSetUpGame.getPlayer1().getCounter() + gain);
                    }
                    else{

                        newSetUpGame.getPlayer1().setCounter(newSetUpGame.getPlayer1().getCounter() - newSetUpGame.getPlayer1().getCharacters().get(currentIndex).getPoint());
                        newSetUpGame.getPlayer1().getCharacters().get(currentIndex).setPoint(0);
                        newSetUpGame.getPlayer2().setCounter(newSetUpGame.getPlayer2().getCounter() - gain);
                    }
                    //newSetUpGame.getPlayer1().getCharacters().get(currentIndex).setPoint(newSetUpGame.getPlayer1().getCharacters().get(currentIndex).getPoint() + gain);
                    newSetUpGame.getPlayer1().setCounter(newSetUpGame.getPlayer1().getCounter() + gain);
                    newSetUpGame.getPlayer2().setCounter(newSetUpGame.getPlayer2().getCounter() - gain);
                }
                else{
                    if(gain > 0)
                    {

                        newSetUpGame.getPlayer2().setCounter(newSetUpGame.getPlayer2().getCounter() + gain);
                        newSetUpGame.getPlayer1().setCounter(newSetUpGame.getPlayer1().getCounter() - newSetUpGame.getPlayer1().getCharacters().get(currentReactiveIndex).getPoint());
                        newSetUpGame.getPlayer1().getCharacters().get(currentReactiveIndex).setPoint(0);
                    }
                    else{

                        newSetUpGame.getPlayer2().setCounter(newSetUpGame.getPlayer2().getCounter() - newSetUpGame.getPlayer2().getCharacters().get(currentIndex).getPoint());
                        newSetUpGame.getPlayer2().getCharacters().get(currentIndex).setPoint(0);
                        newSetUpGame.getPlayer1().setCounter(newSetUpGame.getPlayer1().getCounter() - gain);
                    }
                    //newSetUpGame.getPlayer1().getCharacters().get(currentIndex).setPoint(newSetUpGame.getPlayer1().getCharacters().get(currentIndex).getPoint() + gain);
                }
            }
        }





    }
}
