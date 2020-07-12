package wmaclean.characters.npc;

import wmaclean.Game;

import java.util.Random;

public enum PassiveID {
    Pig{
        @Override
        public PassiveAnimal newPassiveOfType(int x, int y, Game game) {
            return new Pig(x, y, game);
        }
    },
    Hawk{
        @Override
        public PassiveAnimal newPassiveOfType(int x, int y, Game game) {
            return new Hawk(x, y, game);
        }
    };

    public abstract PassiveAnimal newPassiveOfType(int x, int y, Game game);

    public static PassiveAnimal newRandomPassive(int x, int y, Game game){
        Random r = new Random();
        PassiveID randId = PassiveID.values()[r.nextInt(PassiveID.values().length)];

        return randId.newPassiveOfType(x, y, game);
    }
}
