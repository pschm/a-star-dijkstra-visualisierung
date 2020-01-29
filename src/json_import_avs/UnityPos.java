package json_import_avs;

import app.Position;

public class UnityPos {

    private double x = Math.random() * 200;
    private double y = Math.random() * 200;

    public UnityPos(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Position convert() {
        return new Position((int) x, (int) y);
    }

    @Override
    public String toString() {
        return "P("+x + "/" + y+")";
    }
}
