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

    private SpeedControllerGroup leftMotorGearbox = new SpeedControllerGroup(firstLeftMotor, secondLeftMotor);
    private SpeedControllerGroup rightMotorGearbox = new SpeedControllerGroup(firstRightMotor, secondRightMotor);

    private final DifferentialDrive drive = new DifferentialDrive(rightMotorGearbox, leftMotorGearbox);

    public DriveTrainSubsystem() {
        super();
//        leftMotorGearbox.setInverted(true);
//        rightMotorGearbox.setInverted(true);
        SmartDashboard.putNumber("Left motor", firstLeftMotor.get());
        SmartDashboard.putNumber("Right Motor", firstRightMotor.get());
    }

    public DriveTrainSubsystem(int motor, int canID, double speed, double kP, double kI, double kD,
                               double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM) {
        super();
        SmartDashboard.putNumber("Left motor", firstLeftMotor.get());
        SmartDashboard.putNumber("Right Motor", firstRightMotor.get());

        if (motor == Constants.CAN.firstLeftDriveMotor) {
            enableTune(firstLeftMotor, canID, kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM);
        }
        if (motor == Constants.CAN.firstRightDriveMotor) {
            enableTune(firstRightMotor, canID, kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM);
        }
        if (motor == Constants.CAN.secondLeftDriveMotor) {
            enableTune(secondLeftMotor, canID, kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM);
        }
        if (motor == Constants.CAN.secondRightDriveMotor) {
            enableTune(secondRightMotor, canID, kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM);
        }

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

    public double getRotation() {
        double rotationTicks = leftEncoder.getPosition() - rightEncoder.getPosition();
        final double fullRotate = 30;
        final double rotationDeg = rotationTicks/fullRotate;
        final double circle = 360;
        return rotationDeg*circle;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getPosition());
    }
}
