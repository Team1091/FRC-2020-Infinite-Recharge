package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.shared.TargetCoordinate;

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

}
