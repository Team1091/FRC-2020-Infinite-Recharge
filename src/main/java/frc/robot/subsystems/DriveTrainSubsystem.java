package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.AccelerationCurve;

public class DriveTrainSubsystem extends SubsystemBase {

    private final int leftMotorChannel = 1;
    private final int rightMotorChannel = 0;

    private Victor leftMotor = new Victor(leftMotorChannel);
    private Victor rightMotor = new Victor(rightMotorChannel);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);
    private final AccelerationCurve accelerationCurve = new AccelerationCurve(0.5);

    public DriveTrainSubsystem() {
        super();
    }


    public void doArcadeDrive(double speed, double rotation) {
        accelerationCurve.set(speed);
        drive.arcadeDrive(accelerationCurve.getCurrentSpeed(), rotation);
    }
}
