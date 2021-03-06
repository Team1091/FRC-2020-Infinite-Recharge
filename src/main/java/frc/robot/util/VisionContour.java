package frc.robot.util;

public class VisionContour {
    private double centerX;
    private double centerY;
    private double width;
    private double area;
    private double height;

    public VisionContour(double centerX, double centerY, double width, double area, double height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.area = area;
        this.width = width;
        this.height = height;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getWidth() {
        return width;
    }

    public double getArea() {
        return area;
    }

    public double getHeight() {
        return height;
    }

}
