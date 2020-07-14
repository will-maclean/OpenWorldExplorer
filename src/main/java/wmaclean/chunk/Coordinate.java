package wmaclean.chunk;

public class Coordinate {
    public Float x, y;

    public Coordinate(){}

    public Coordinate(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Coordinate)){
            return false;
        }

        Coordinate other = (Coordinate) obj;

        return this.x.equals(other.x) && this.y.equals(other.y);
    }
}
