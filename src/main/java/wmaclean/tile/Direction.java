package wmaclean.tile;

public enum Direction {
    UP{
        @Override
        public float xOffset() {
            return 0;
        }

        @Override
        public float yOffset() {
            return -1 * TileChunk.CHUNKSIZE;
        }
    },
    DOWN{
        @Override
        public float xOffset() {
            return 0;
        }

        @Override
        public float yOffset() {
            return TileChunk.CHUNKSIZE;
        }
    },
    LEFT{
        @Override
        public float xOffset() {
            return -1 * TileChunk.CHUNKSIZE;
        }

        @Override
        public float yOffset() {
            return 0;
        }
    },
    RIGHT{
        @Override
        public float xOffset() {
            return TileChunk.CHUNKSIZE;
        }

        @Override
        public float yOffset() {
            return 0;
        }
    };

    public abstract float xOffset();
    public abstract float yOffset();
}
