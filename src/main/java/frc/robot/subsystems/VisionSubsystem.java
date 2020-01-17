package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TargetCoordinate;
import frc.robot.util.VisionBlob;
import frc.robot.util.VisionContour;

public class VisionSubsystem extends SubsystemBase {
    private UsbCamera visionFeed = new UsbCamera("Camera1", 0);
    private NetworkTableInstance networkTable  = null;
    private NetworkTable blobNetworkTable = null;
    private NetworkTable contoursNetworkTable = null;

    private final int cameraWidth = 640;
    private final int cameraHeight = 480;

    public VisionSubsystem(){
        visionFeed.setResolution(cameraWidth,cameraHeight);
        CameraServer.getInstance().startAutomaticCapture(visionFeed);
        networkTable = NetworkTableInstance.getDefault();
        blobNetworkTable = NetworkTableInstance.getDefault().getTable("GRIP/myBlobsReport");
        contoursNetworkTable = NetworkTableInstance.getDefault().getTable("GRIP/myContoursReport");

    }

    public boolean targetInSight() {
        VisionContour[] contours = getContours();
        boolean seen = false;
        for(VisionContour contour : contours){
            if (contour.getArea() > 5)
                seen = true;
        }
        return seen;
    }

    public double getDistanceToTarget () {
        //todo implement
        return 0.0;
    }

    public TargetCoordinate getTargetCoordinates() {
        VisionContour largestContour = null;
        for (VisionContour contour : getContours()){
            if (largestContour == null)
                largestContour = contour;
            else if (largestContour.getArea() < contour.getArea())
                    largestContour = contour;
        }

        if (largestContour == null)
            return null;

        double cameraWidthCenter = (double) cameraWidth / 2;
        double cameraHeightCenter = (double) cameraHeight / 2;
        double contourX = (double) largestContour.getCenterX() / cameraWidthCenter - 1;
        double contourY = (double) largestContour.getCenterY() / cameraHeightCenter + 1;
        return new TargetCoordinate(contourX, contourY);
    }


    public VisionBlob[] getBlobs(){
        double[] xs = blobNetworkTable.getEntry("x").getDoubleArray(new double[0]);
        double[] ys = blobNetworkTable.getEntry("y").getDoubleArray(new double[0]);
        double[] sizes = blobNetworkTable.getEntry("size").getDoubleArray(new double[0]);
        VisionBlob[] blobs = new VisionBlob[sizes.length];

        for (int i = 0; sizes.length < i; i++) {
            blobs[i] = new VisionBlob(xs[i], ys[i], sizes[i]);
        }

        return blobs;
    }

    public VisionContour[] getContours(){
        double[] centerXs = contoursNetworkTable.getEntry("centerX").getDoubleArray(new double[0]);
        double[] centerYs = contoursNetworkTable.getEntry("centerY").getDoubleArray(new double[0]);
        double[] widths = contoursNetworkTable.getEntry("width").getDoubleArray(new double[0]);
        double[] areas = contoursNetworkTable.getEntry("area").getDoubleArray(new double[0]);
        double[] heights = contoursNetworkTable.getEntry("height").getDoubleArray(new double[0]);
        double[] soliditys = contoursNetworkTable.getEntry("solidity").getDoubleArray(new double[0]);
        VisionContour[] contours = new VisionContour[centerXs.length];

        for (int i = 0; centerXs.length < i; i++) {
            contours[i] = new VisionContour(centerXs[i], centerYs[i], widths[i], areas[i], heights[i], soliditys[i]);
        }

        return contours;
    }

    @Override
    public void periodic() {

        VisionContour[] contours = getContours();
        VisionBlob[] blobs = getBlobs();
        SmartDashboard.putNumber("Countoursfound", contours.length);
        SmartDashboard.putNumber("Blobfound", blobs.length);
        SmartDashboard.putBoolean("targetseen", targetInSight());
        TargetCoordinate coordinate = getTargetCoordinates();
        if (coordinate != null)
            SmartDashboard.putString("coordinate", "X: " + coordinate.getX() + " Y: " + coordinate.getY());
        else
            SmartDashboard.putString("coordinate", "not found");
        

    }
}
