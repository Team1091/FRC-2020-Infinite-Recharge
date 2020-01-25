package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.AccelerationCurve;

import static frc.robot.Constants.*;

public class DriveTrainSubsystem extends SubsystemBase {


    private Victor leftMotor = new Victor(Constants.leftDriveMotor);
    private Victor rightMotor = new Victor(Constants.rightDriveMotor);
    private Encoder leftEncoder = new Encoder(leftEncoderA, leftEncoderB);
    private Encoder rightEncoder = new Encoder(rightEncoderA, rightEncoderB);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);
    private final AccelerationCurve speedCurve = new AccelerationCurve(4, .375, .8);
    private final AccelerationCurve rotationCurve = new AccelerationCurve(4, .375, .8);

    public DriveTrainSubsystem() {
        super();
        SmartDashboard.putData("Left motor", leftMotor);
        SmartDashboard.putData("Right Motor", rightMotor);
    }


    public void doArcadeDrive(double speed, double rotation) {
        speedCurve.set(speed);
        rotationCurve.set(rotation);
        drive.arcadeDrive(speedCurve.getCurrentSpeed(), rotationCurve.getCurrentSpeed());
        SmartDashboard.putNumber("Target Drive Speed", speed);
        SmartDashboard.putNumber("Current Drive Speed", speedCurve.getCurrentSpeed());
    }

    public void reset() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }
}
