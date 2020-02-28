package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TargetCoordinate;
import frc.robot.util.VisionContour;

import java.text.DecimalFormat;

public class VisionSubsystem extends SubsystemBase {
    private UsbCamera visionFeed = new UsbCamera("Camera1", 0);
    private Timer timer = new Timer();
    private NetworkTable contoursNetworkTable;

    private final int cameraWidth = 160;
    private final int cameraHeight = 120;
    private final double targetPhysWidth = 35;
    private final double targetPhysHeight = 17.5;
    private final double contourRatio = 2.0; //targetPhysHeight / targetPhysHeight;
    private final double contourRatioTolerance = .25;

    public VisionSubsystem() {
        timer.start();
        visionFeed.setResolution(cameraWidth, cameraHeight);
        CameraServer.getInstance().startAutomaticCapture(visionFeed);
        contoursNetworkTable = NetworkTableInstance.getDefault().getTable("SNIP/myContoursReport");

    }


    public Double getDistanceToTarget() {
        VisionContour largestContour = getTarget();
        if (largestContour == null) {
            return null;
        }
        double viewAngle = 44.5;
        return ((targetPhysWidth / 12.0) * (double) cameraWidth) / ((2.0 * largestContour.getWidth()) * Math.tan(viewAngle));
    }

    public VisionContour getTarget() {
        VisionContour largestContour = null;
        for (VisionContour contour : getContours()) {
            var ratio = contour.getWidth() / contour.getHeight();
            if (ratio - contourRatioTolerance > contourRatio || ratio + contourRatioTolerance < contourRatio) {
                continue;
            }
            if (largestContour == null) {
                largestContour = contour;
                continue;
            }
            if (largestContour.getArea() < contour.getArea()) {
                largestContour = contour;
            }
        }
        if (largestContour != null)
            SmartDashboard.putString("Target Ratio", "Ratio: " + (largestContour.getWidth() / largestContour.getHeight()) + " - Should Be:" + contourRatio);
        return largestContour;
    }

    public TargetCoordinate getTargetCoordinates() {
        VisionContour largestContour = getTarget();
        if (largestContour == null)
            return null;

        SmartDashboard.putNumber("CenterX", largestContour.getCenterX());
        SmartDashboard.putNumber("CenterY", largestContour.getCenterY());
        SmartDashboard.putNumber("Target Width", largestContour.getWidth());
        double cameraWidthCenter = (double) cameraWidth / 2.0;
        double cameraHeightCenter = (double) cameraHeight / 2.0;
        double contourX = largestContour.getCenterX() / cameraWidthCenter - 1;
        double contourY = -largestContour.getCenterY() / cameraHeightCenter + 1;
        return new TargetCoordinate(contourX, contourY);
    }

    public VisionContour[] getContours() {
        double[] centerXs = contoursNetworkTable.getEntry("centerX").getDoubleArray(new double[0]);
        double[] centerYs = contoursNetworkTable.getEntry("centerY").getDoubleArray(new double[0]);
        double[] widths = contoursNetworkTable.getEntry("width").getDoubleArray(new double[0]);
        double[] areas = contoursNetworkTable.getEntry("area").getDoubleArray(new double[0]);
        double[] heights = contoursNetworkTable.getEntry("height").getDoubleArray(new double[0]);

        VisionContour[] contours = new VisionContour[centerXs.length];

        for (int i = 0; centerXs.length > i; i++) {
            var width = getIForIndexOr0(widths, i);
            var height = getIForIndexOr0(heights, i);

            contours[i] = new VisionContour(
                    getIForIndexOr0(centerXs, i) + width / 2.0,
                    getIForIndexOr0(centerYs, i) + height / 2.0,
                    getIForIndexOr0(widths, i),
                    getIForIndexOr0(areas, i),
                    getIForIndexOr0(heights, i)
            );
        }

        return contours;
    }

    private double getIForIndexOr0(double[] arr, int idx) {
        if (idx < arr.length) {
            return arr[idx];
        }
        return 0.0;
    }

    DecimalFormat df = new DecimalFormat("0.0000");

    @Override
    public void periodic() {
        if (timer.hasPeriodPassed(.2)) {
            Double distanceToTarget = getDistanceToTarget();
            VisionContour[] contours = getContours();
            SmartDashboard.putNumber("Distance To Target",distanceToTarget != null ? distanceToTarget : -1 );
            SmartDashboard.putNumber("Total Contours", contours.length);
            TargetCoordinate coordinate = getTargetCoordinates();
            if (coordinate != null) {
                SmartDashboard.putString("coordinate", "X: " + df.format(coordinate.getX()) + " Y: " + df.format(coordinate.getY()));
            } else {
                SmartDashboard.putString("coordinate", "not found");
            }
        }
    }

}
