package wmaclean.characters;

import java.util.Random;

public enum SpriteDirection {
    up{
        @Override
        public int directionIndex() {
            return 0;
        }
    },
    down{
        @Override
        public int directionIndex() {
            return 2;
        }
    },
    left{
        @Override
        public int directionIndex() {
            return 1;
        }
    },
    right{
        @Override
        public int directionIndex() {
            return 3;
        }
    };

    public static SpriteDirection random() {
        Random r = new Random();
        int index = r.nextInt(SpriteDirection.values().length);
        return SpriteDirection.values()[index];
    }

    public abstract int directionIndex();
}
