package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private Victor shooterMotor = new Victor(Constants.shooterMotor);
    private Encoder shooterMotorEncoder = new Encoder(Constants.shooterEncoderA, Constants.shooterEncoderB);

    public ShooterSubsystem() {
        super();
    }

    public void runShooterMotor(double speed) {
        shooterMotor.set(speed);
    }

    public double getSpeed() {
        return shooterMotorEncoder.getRate();
    }
}
