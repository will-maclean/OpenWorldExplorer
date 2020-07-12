package wmaclean.characters.npc;

import wmaclean.Game;
import wmaclean.characters.CharID;
import wmaclean.characters.Character;

public abstract class NPC extends Character {

    protected int health;

    public NPC(float x, float y, Game game) {
        super(x, y, game, CharID.NPC);
    }
}
