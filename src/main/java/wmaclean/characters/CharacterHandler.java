package wmaclean.characters;

import wmaclean.Game;
import wmaclean.characters.npc.NPC;
import wmaclean.characters.npc.PassiveAnimal;
import wmaclean.characters.npc.PassiveID;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CharacterHandler {

    private final List<Character> characterList;
    private final Game game;

    private static final float PASSIVE_SPAWN_RATE = 0.0002f;
    private static final int NPC_MAX_DIST = 200;

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

        Collections.sort(this.characterList);

        if(Math.random() < PASSIVE_SPAWN_RATE){
            spawnRandomPassive();
        }

        List<Character> removeList = new LinkedList<>();
        for(Character character : this.characterList){
            character.tick();
            if(character instanceof NPC){
                despawnNPC(character, removeList);
            }
        }

        this.characterList.removeAll(removeList);
    }

    /**
     * Despawns NPCs when they get too far away from the screen
     * @param character - NPC to check
     * @param removeList - put NPCs in here if bad, then remove when required
     */
    private void despawnNPC(Character character, List<Character> removeList) {
        if(!Game.between(-1 * this.game.posX - NPC_MAX_DIST, -1 * this.game.posX + Game.WIDTH + NPC_MAX_DIST, character.x)
                || !Game.between(-1 * this.game.posY - NPC_MAX_DIST, -1 * this.game.posY + Game.HEIGHT + NPC_MAX_DIST, character.y)){
            removeList.add(character);
        }
    }

    private void spawnRandomPassive() {
        // want the new passive to spawn within 100 units of the screen
        int spawnWithin = 100;
        Random r = new Random();
        int x = r.nextInt(spawnWithin * 2) - 100;
        if(x > 0){
            x = (int) (x + Game.WIDTH - this.game.posX);
        }else{
            x = (int) (-1 * this.game.posX - x);
        }

        int y = r.nextInt(spawnWithin * 2) - 100;
        if(y > 0){
            y = (int) (y + Game.HEIGHT - this.game.posY);
        }else{
            y = (int) (-1 * this.game.posY - y);
        }


        PassiveAnimal newPassive = PassiveID.newRandomPassive(x, y, this.game);
        this.characterList.add(newPassive);
    }

     public int getCharacterListSize(){
        return this.characterList.size();
     }
}
