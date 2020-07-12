package wmaclean.chunk;

public enum Direction {
    UP{
        @Override
        public float xChunkOffset() {
            return 0;
        }

        @Override
        public float yChunkOffset() {
            return -1 * TileChunk.CHUNK_SIZE;
        }
    },
    DOWN{
        @Override
        public float xChunkOffset() {
            return 0;
        }

        @Override
        public float yChunkOffset() {
            return TileChunk.CHUNK_SIZE;
        }
    },
    LEFT{
        @Override
        public float xChunkOffset() {
            return -1 * TileChunk.CHUNK_SIZE;
        }

        @Override
        public float yChunkOffset() {
            return 0;
        }
    },
    RIGHT{
        @Override
        public float xChunkOffset() {
            return TileChunk.CHUNK_SIZE;
        }

        @Override
        public float yChunkOffset() {
            return 0;
        }
    };

    public abstract float xChunkOffset();
    public abstract float yChunkOffset();
}
