package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static java.lang.StrictMath.abs;

public class ShooterSubsystem extends TunablePidSubsystem {
    private CANSparkMax firstShooterMotor = new CANSparkMax(Constants.CAN.firstShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondShooterMotor = new CANSparkMax(Constants.CAN.secondShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder firstShooterEncoder = new CANEncoder(firstShooterMotor);
    private CANEncoder secondShooterEncoder = new CANEncoder(secondShooterMotor);

    private CANPIDController firstShooterPIDControl = firstShooterMotor.getPIDController();
    private CANPIDController secondShooterPIDControl = firstShooterMotor.getPIDController();

    public ShooterSubsystem() {
        super();
        init();
    }

    public ShooterSubsystem(int motor, int canID, double kP, double kI, double kD,
                            double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM) {
        super();
        init();

        if (motor == Constants.CAN.firstShooterMotor) {
            enableTune(firstShooterMotor, canID, kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM);
        }

        if (motor == Constants.CAN.secondShooterMotor) {
            enableTune(secondShooterMotor, canID, kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM);
        }
    }

    private void init() {
        secondShooterMotor.setInverted(true);
    }

    public void runShooter(double speed) {
        firstShooterMotor.set(speed);
        secondShooterMotor.set(speed);
    }

    public double getSpeed() {
        var shooterSpeed = 0.0;
        double percentOff = (abs(secondShooterEncoder.getVelocity()) / abs(firstShooterEncoder.getVelocity()));
        if (percentOff < 1.10 || percentOff > 0.90) {
            shooterSpeed = abs(firstShooterEncoder.getVelocity());
        } else {
            shooterSpeed = 0.0; //TODO: Replace this check system with a PID loop
        }
        SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
        return shooterSpeed;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("First Shooter Speed Encoder", firstShooterEncoder.getVelocity());
        SmartDashboard.putNumber("Second Shooter Speed Encoder", secondShooterEncoder.getVelocity());
        if (super.enableTune) {
            super.tunePeriodic();
        }
    }
}