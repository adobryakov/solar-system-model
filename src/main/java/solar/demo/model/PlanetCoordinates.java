package solar.demo.model;

/**
 * Planet coordinates from the center of the current solar system or any other coordinate base
 */
public class PlanetCoordinates {
    private long x;
    private long y;

    public static PlanetCoordinates CENTER = new PlanetCoordinates(0L, 0L);

    public PlanetCoordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public PlanetCoordinates plus(PlanetCoordinates addition) {
        return new PlanetCoordinates(this.x + addition.x, this.y + addition.y);
    }

    public long distanceTo(PlanetCoordinates nextPoint) {
        return (long) Math.sqrt( Math.pow(this.x-nextPoint.x, 2) + Math.pow(this.y-nextPoint.y, 2));
    }

    public long module() {
        return (long) Math.sqrt( Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
}
