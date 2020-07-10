package wmaclean.characters;

import wmaclean.Game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class CharacterHandler {

    public final List<Character> characterList;
    private Game game;

    public CharacterHandler(Game game) {
        characterList = new LinkedList<>();
        this.game = game;
    }

    public void setCharacter(Character character) {
        this.characterList.add(character);
    }

    public void render(Graphics g) {

        for(Character character : this.characterList){
            character.render(g);
        }
    }

    public Player getPlayer(){
        for(Character character : this.characterList){
            if(character instanceof Player){
                return (Player)character;
            }
        }

        return null;
    }

    public void tick() {
        for(Character character : this.characterList){
            character.tick();
        }
    }
}
