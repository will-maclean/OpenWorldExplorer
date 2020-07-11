package wmaclean.tile;

public enum Direction {
    UP{
        @Override
        public float xChunkOffset() {
            return 0;
        }

        @Override
        public float yChunkOffset() {
            return -1 * TileChunk.CHUNKSIZE;
        }
    },
    DOWN{
        @Override
        public float xChunkOffset() {
            return 0;
        }

        @Override
        public float yChunkOffset() {
            return TileChunk.CHUNKSIZE;
        }
    },
    LEFT{
        @Override
        public float xChunkOffset() {
            return -1 * TileChunk.CHUNKSIZE;
        }

        @Override
        public float yChunkOffset() {
            return 0;
        }
    },
    RIGHT{
        @Override
        public float xChunkOffset() {
            return TileChunk.CHUNKSIZE;
        }

        @Override
        public float yChunkOffset() {
            return 0;
        }
    };

    public abstract float xChunkOffset();
    public abstract float yChunkOffset();
}
