package wmaclean.characters;

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

    public abstract int directionIndex();
}
