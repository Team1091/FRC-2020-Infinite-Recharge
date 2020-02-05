package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TargetCoordinate;
import frc.robot.util.VisionBlob;
import frc.robot.util.VisionContour;

public class VisionSubsystem extends SubsystemBase {
    private UsbCamera visionFeed = new UsbCamera("Camera1", 0);
    private Timer timer = new Timer();
    private NetworkTableInstance networkTable  = null;
    private NetworkTable blobNetworkTable = null;
    private NetworkTable contoursNetworkTable = null;

    private final int cameraWidth = 480;
    private final int cameraHeight = 270;

    public VisionSubsystem(){
        timer.start();
        visionFeed.setResolution(cameraWidth,cameraHeight);
        CameraServer.getInstance().startAutomaticCapture(visionFeed);
        networkTable = NetworkTableInstance.getDefault();
        //blobNetworkTable = NetworkTableInstance.getDefault().getTable("GRIP/myBlobsReport");
        contoursNetworkTable = NetworkTableInstance.getDefault().getTable("SNIP/myContoursReport");

    }

    public boolean targetInSight() {
        VisionContour[] contours = getContours();
        boolean seen = false;
        for(VisionContour contour : contours) {
            if (contour != null && contour.getArea() > 5)
                seen = true;
        }
        return seen;
    }

    public double getDistanceToTarget () {
        VisionContour largestContour = getLargestContour();
        if (largestContour == null){
            return -1;
        }
        double targetPhysWidth = 35;
        double targetPhysHeight = 17.5;
        double ratio = targetPhysWidth/targetPhysHeight;
        double visibleTargetRatio = largestContour.getWidth() / largestContour.getHeight();
        //epislon is the acceptable tolerance
        double epislon = 0.5;
        SmartDashboard.putNumber("Contour ratio", visibleTargetRatio);
        if (ratio - epislon > visibleTargetRatio || ratio + epislon < visibleTargetRatio) {
            return -1;
        }
        double viewAngle = 44.5;
        SmartDashboard.putNumber("Contour Width", largestContour.getWidth());

        double distance = ((targetPhysWidth / 12.0) * (double) cameraWidth) /  ((2.0 * largestContour.getWidth()) * Math.tan(viewAngle));
        return distance;
    }
    public VisionContour getLargestContour(){
        VisionContour largestContour = null;
        for (VisionContour contour : getContours()){
            if (largestContour == null)
                largestContour = contour;
            else if (largestContour.getArea() < contour.getArea())
                largestContour = contour;
        }
        return largestContour;
    }

    public TargetCoordinate getTargetCoordinates() {
        VisionContour largestContour = getLargestContour();
        if (largestContour == null)
            return null;

        SmartDashboard.putNumber("CenterX", largestContour.getCenterX());
        SmartDashboard.putNumber("CenterY", largestContour.getCenterY());
        double cameraWidthCenter = (double) cameraWidth / 2;
        double cameraHeightCenter = (double) cameraHeight / 2;
        double contourX = (double) largestContour.getCenterX() / cameraWidthCenter - 1;
        double contourY = (double) -largestContour.getCenterY() / cameraHeightCenter + 1;
        return new TargetCoordinate(contourX, contourY);
    }


//    public VisionBlob[] getBlobs(){
////        double[] xs = blobNetworkTable.getEntry("x").getDoubleArray(new double[0]);
////        double[] ys = blobNetworkTable.getEntry("y").getDoubleArray(new double[0]);
////        double[] sizes = blobNetworkTable.getEntry("size").getDoubleArray(new double[0]);
////        VisionBlob[] blobs = new VisionBlob[sizes.length];
////
////        for (int i = 0; sizes.length > i; i++) {
////            blobs[i] = new VisionBlob(xs[i], ys[i], sizes[i]);
////        }
////
////        return blobs;
////    }

    public VisionContour[] getContours(){
        double[] centerXs = contoursNetworkTable.getEntry("centerX").getDoubleArray(new double[0]);
        double[] centerYs = contoursNetworkTable.getEntry("centerY").getDoubleArray(new double[0]);
        double[] widths = contoursNetworkTable.getEntry("width").getDoubleArray(new double[0]);
        double[] areas = contoursNetworkTable.getEntry("area").getDoubleArray(new double[0]);
        double[] heights = contoursNetworkTable.getEntry("height").getDoubleArray(new double[0]);
        //double[] soliditys = contoursNetworkTable.getEntry("solidity").getDoubleArray(new double[0]);
        VisionContour[] contours = new VisionContour[centerXs.length];

        for (int i = 0; centerXs.length > i; i++) {
            contours[i] = new VisionContour(centerXs[i], centerYs[i], widths[i], areas[i], heights[i], 0.0);
        }

        return contours;
    }

    @Override
    public void periodic() {
        if(timer.hasPeriodPassed(.2)) {
            VisionContour[] contours = getContours();
            //VisionBlob[] blobs = getBlobs();
            SmartDashboard.putNumber("Countoursfound", contours.length);
            //SmartDashboard.putNumber("Blobfound", blobs.length);
            SmartDashboard.putBoolean("targetseen", targetInSight());
            SmartDashboard.putNumber("distancetotarget",getDistanceToTarget());
            SmartDashboard.putNumber("contour count",contours.length);
            TargetCoordinate coordinate = getTargetCoordinates();
            if (coordinate != null) {
                SmartDashboard.putString("coordinate", "X: " + coordinate.getX() + " Y: " + coordinate.getY());

            }
            else{
                SmartDashboard.putString("coordinate", "not found");

            }
        }
    }

}
