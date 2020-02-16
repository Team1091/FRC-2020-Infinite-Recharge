package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveTrainSubsystem extends TunablePidSubsystem {

    private CANSparkMax firstLeftMotor = new CANSparkMax(Constants.CAN.firstLeftDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondLeftMotor = new CANSparkMax(Constants.CAN.secondLeftDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax firstRightMotor = new CANSparkMax(Constants.CAN.firstRightDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondRightMotor = new CANSparkMax(Constants.CAN.secondRightDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    //TODO: finish more than basic implementation for two motors on both sides
    private CANEncoder leftEncoder = new CANEncoder(firstLeftMotor);
    private CANEncoder rightEncoder = new CANEncoder(firstRightMotor);

    private SpeedControllerGroup leftMotorGearbox = new SpeedControllerGroup(firstLeftMotor,secondLeftMotor);
    private SpeedControllerGroup rightMotorGearbox = new SpeedControllerGroup(firstRightMotor, secondRightMotor);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotorGearbox,rightMotorGearbox);

    public DriveTrainSubsystem() {
        super();
        SmartDashboard.putNumber("Left motor", firstLeftMotor.get());
        SmartDashboard.putNumber("Right Motor", firstRightMotor.get());

    }

    public void doArcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public void reset() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public double getDistance() {
        return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
    }
    public double getRotation(){
        return leftEncoder.getPosition()-rightEncoder.getPosition();
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getPosition());
    }
}
