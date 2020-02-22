package frc.robot.util;

public class VisionBlob {
    private double x;
    private double y;
    private double size;

    public VisionBlob(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
