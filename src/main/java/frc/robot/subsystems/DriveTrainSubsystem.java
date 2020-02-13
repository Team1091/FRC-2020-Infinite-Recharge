package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.AccelerationCurve;

public class DriveTrainSubsystem extends SubsystemBase {


    private CANSparkMax leftMotor =  new CANSparkMax(Constants.leftDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax rightMotor = new CANSparkMax(Constants.rightDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder leftEncoder = new CANEncoder(leftMotor);
    private CANEncoder rightEncoder = new CANEncoder(rightMotor);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);
    private final AccelerationCurve speedCurve = new AccelerationCurve(4, .375, .8);
    private final AccelerationCurve rotationCurve = new AccelerationCurve(4, .375, .8);

    public DriveTrainSubsystem() {
        super();
        SmartDashboard.putNumber("Left motor", leftMotor.get());
        SmartDashboard.putNumber("Right Motor", rightMotor.get());

    }

    public void doArcadeDrive(double speed, double rotation) {
        speedCurve.set(speed);
        rotationCurve.set(rotation);
        drive.arcadeDrive(speedCurve.getCurrentSpeed(), rotationCurve.getCurrentSpeed());
    }

    public void reset() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public double getDistance() {
        return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getPosition());
        doArcadeDrive(0,0);
    }
}
