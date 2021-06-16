package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.util.PIDTuner;

public class DriveTrainSubsystem extends PIDTunableSubsystem {

    private CANSparkMax firstLeftMotor = new CANSparkMax(Constants.CAN.firstLeftDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondLeftMotor = new CANSparkMax(Constants.CAN.secondLeftDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax firstRightMotor = new CANSparkMax(Constants.CAN.firstRightDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondRightMotor = new CANSparkMax(Constants.CAN.secondRightDriveMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder leftEncoder = firstLeftMotor.getEncoder();
    private CANEncoder rightEncoder = firstRightMotor.getEncoder();

    private SpeedControllerGroup leftMotorGearbox = new SpeedControllerGroup(firstLeftMotor);  // private SpeedControllerGroup leftMotorGearbox = new SpeedControllerGroup(firstLeftMotor);
    private SpeedControllerGroup rightMotorGearbox = new SpeedControllerGroup(firstRightMotor);//, secondRightMotor

    private final DifferentialDrive drive = new DifferentialDrive(rightMotorGearbox, leftMotorGearbox);

    public DriveTrainSubsystem() {
        super();
        firstRightMotor.setInverted(Constants.rightDriveMotorInverted);
        secondRightMotor.setInverted(Constants.rightDriveMotorInverted);
        firstLeftMotor.setInverted(Constants.leftDriveMotorInverted);
        secondLeftMotor.setInverted(Constants.leftDriveMotorInverted);

        SmartDashboard.putNumber("Left motor", firstLeftMotor.get());
        SmartDashboard.putNumber("Right Motor", firstRightMotor.get());
    }

    @Override
    public PIDTuner[] getPidTuners() {
        var tuners = new PIDTuner[4];
        tuners[0] = new PIDTuner(firstLeftMotor, "DriveL1");
        tuners[1] = new PIDTuner(secondLeftMotor, "DriveL2");
        tuners[2] = new PIDTuner(firstRightMotor, "DriveR1");
        tuners[3] = new PIDTuner(secondRightMotor, "DriveR2");
        return tuners;
    }

    public void doArcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public double getDistance() {
        return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
    }

    public double getDistanceFeet() {
        var distance = getDistance();
        return distance / Constants.encoderToFootRatio;

    }

    public double getRotation() {
        double rotationTicks = leftEncoder.getPosition() - rightEncoder.getPosition();
        final double fullRotate = 30;
        final double rotationDeg = rotationTicks / fullRotate;
        final double circle = 360;
        return rotationDeg * circle;
    }

    public void setMotorSpeed(double left, double right) {
        leftMotorGearbox.set(left);
        rightMotorGearbox.set(right);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getPosition());
    }
}
