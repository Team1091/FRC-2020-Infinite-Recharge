package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private Victor shooterMotor = new Victor(Constants.shooterMotor);
    private Victor aimMotor = new Victor(Constants.aimMotor);
    private Encoder aimMotorEncoder = new Encoder(Constants.aimEncoderA, Constants.aimEncoderB);
    private DigitalInput pickUpLimitSwitch = new DigitalInput(Constants.pickUpLimitSwitch);
    private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.shooterLimitSwitch);
    private final int pickUpPosition = 0;
    private final int shootPosition = 0;
    private final double aimMotorSpeed = 0.5;
    private final int aimPositionDelta = 10;

    public ShooterSubsystem() {
        super();
    }

    public void runShooterMotor(double speed) {
        shooterMotor.set(speed);
    }

    public void runAimMotor(double speed) {
        aimMotor.set(speed);
    }

    public void goToShootPosition() {
        runAimMotor(0);
        if (shooterLimitSwitch.get()) {
            return;
        }
        runAimMotor(aimMotorSpeed);
    }

    public void goToPickupPosition() {
        runAimMotor(0);
        if (pickUpLimitSwitch.get()) {
            aimMotorEncoder.reset();
            return;
        }
        runAimMotor(-aimMotorSpeed);
    }

    public boolean isInShootPosition() {
        return shooterLimitSwitch.get();
    }

    public boolean isAtPickUpPosition() {
        return pickUpLimitSwitch.get();
    }

    public void setAim(int encoderPosition) {
        runAimMotor(0);
        if (encoderPosition < 0) {
            encoderPosition = 0;
        }
        if (shooterLimitSwitch.get()) {
            encoderPosition = aimMotorEncoder.get();
        }
        if (aimMotorEncoder.get() == encoderPosition ||
                aimMotor.get() <= encoderPosition + aimPositionDelta ||
                aimMotor.get() >= encoderPosition - aimPositionDelta) {
            return;
        }
        if (aimMotorEncoder.get() > encoderPosition) {
            runAimMotor(aimMotorSpeed);
            return;
        }
        if (aimMotorEncoder.get() < encoderPosition) {
            runAimMotor(-aimMotorSpeed);
            return;
        }
    }
}
