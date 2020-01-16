package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TargetCoordinate;

public class VisionSubsystem extends SubsystemBase {
    private UsbCamera visionFeed = new UsbCamera("Camera1", 0);
    private NetworkTableInstance networkTable  = null;
    private NetworkTable gripNetworkTable  = null;


    public VisionSubsystem(){
        visionFeed.setResolution(640, 480);
        CameraServer.getInstance().startAutomaticCapture(visionFeed);
        networkTable = NetworkTableInstance.getDefault();
        gripNetworkTable = NetworkTableInstance.getDefault().getTable("GRIP/myBlobsReport");
    }

    public boolean targetInSight() {
        //todo implement
        return false;
    }

    public double getDistanceToTarget () {
        //todo implement
        return 0.0;
    }

    public TargetCoordinate getTargetCoordinates() {
        //todo implement
        return null;
    }

    @Override
    public void periodic() {
        double[] xs = gripNetworkTable.getEntry("x").getDoubleArray(new double[0]);
        double[] ys = gripNetworkTable.getEntry("y").getDoubleArray(new double[0]);
        double[] sizes = gripNetworkTable.getEntry("size").getDoubleArray(new double[0]);
        if(xs.length > 0){
            SmartDashboard.putNumber("X IS", xs[0]);
        } else {
            SmartDashboard.putNumber("X IS", -1);
        }
        if(ys.length > 0){
            SmartDashboard.putNumber("Y IS", ys[0]);
        } else {
            SmartDashboard.putNumber("Y IS", -1);
        }
        if(sizes.length > 0){
            SmartDashboard.putNumber("Size IS", sizes[0]);
        } else {
            SmartDashboard.putNumber("Size IS", -1);
        }
    }
}
