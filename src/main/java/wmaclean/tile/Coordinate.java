package wmaclean.tile;

public class Coordinate {
    public float x, y;

    public Coordinate(){}

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Coordinate)){
            return false;
        }

        Coordinate other = (Coordinate) obj;

        return this.x == other.x && this.y == other.y;
    }
}
