package Implementations;

import Classes.Player;
import Classes.Character;
import Contracts.Settings;

import java.util.ArrayList;

public class setUpGame implements Settings {

        private ArrayList<String> playerNames;
        private Player player1;
        private Player player2;
        private static ArrayList<String> characterNames;

        public setUpGame(ArrayList<String> playerNames){
            this.playerNames = playerNames;
        }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ArrayList<String> getCharacterNames() {
        return characterNames;
    }

    public void setCharacterNames(ArrayList<String> characterNames) {
        setUpGame.characterNames = characterNames;
    }

    public static void updateCharacters(String characterName){
            characterNames.remove(characterName);
        }
        @Override
        public ArrayList<String> identifyPlayer(String player1, String player2) {
            this.playerNames.add(player1);
            this.playerNames.add(player2);
            return playerNames;
        }

        @Override
        public ArrayList<Character> getCharacters() {
            return getCharacters();
        }

        @Override
        public ArrayList<Player> createTeams(ArrayList<String> personnages1, ArrayList<String> personnages2) {
            ArrayList<Player> players = new ArrayList<>();
            player1 = new Player(this.playerNames.get(0) ,personnages1);
            player2 = new Player(this.playerNames.get(1) ,personnages2);
            return players;
        }


    @Override
    public ArrayList<Character> setCharacters() {
        return null;
    }
}

