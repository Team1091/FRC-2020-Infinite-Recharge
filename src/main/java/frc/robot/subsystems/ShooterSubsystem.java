package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.util.PIDTuner;

import static java.lang.StrictMath.abs;

public class ShooterSubsystem extends PIDTunableSubsystem {
    private CANSparkMax leftShooterMotor = new CANSparkMax(Constants.CAN.leftShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax rightShooterMotor = new CANSparkMax(Constants.CAN.rightShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder leftShooterEncoder = new CANEncoder(leftShooterMotor);
    private CANEncoder rightShooterEncoder = new CANEncoder(rightShooterMotor);

    public ShooterSubsystem() {
        super();
        rightShooterMotor.setInverted(Constants.rightShooterMotorInverted);
        leftShooterMotor.setInverted(Constants.leftShooterMotorInverted);
    }

    @Override
    public PIDTuner[] getPidTuners() {
        var pidTuners = new PIDTuner[2];
        pidTuners[0] = new PIDTuner(leftShooterMotor, "Shooter1");
        pidTuners[1] = new PIDTuner(rightShooterMotor, "Shooter2");
        return pidTuners;
    }

    public void runShooter(double speed) {
        leftShooterMotor.set(speed);
        rightShooterMotor.set(speed);
    }

    public CANPIDController getPIDRightController() {
        return rightShooterMotor.getPIDController();
    }

    public CANPIDController getPIDLeftController() {
        return leftShooterMotor.getPIDController();
    }

    public double getSpeed() {
        var shooterSpeed = 0.0;
        double percentOff = (abs(rightShooterEncoder.getVelocity()) / abs(leftShooterEncoder.getVelocity()));
        if (percentOff < 1.10 || percentOff > 0.90) {
            shooterSpeed = abs(leftShooterEncoder.getVelocity());
        } else {
            shooterSpeed = 0.0;
        }
        SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
        return shooterSpeed;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("First Shooter Speed Encoder", leftShooterEncoder.getVelocity());
        SmartDashboard.putNumber("Second Shooter Speed Encoder", rightShooterEncoder.getVelocity());
    }
}