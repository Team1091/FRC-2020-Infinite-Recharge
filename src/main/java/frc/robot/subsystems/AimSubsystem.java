package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AimSubsystem extends SubsystemBase {

    private Victor aimMotor = new Victor(Constants.aimMotor);
    private Encoder aimMotorEncoder = new Encoder(Constants.aimEncoderA, Constants.aimEncoderB);
    private DigitalInput pickUpLimitSwitch = new DigitalInput(Constants.pickUpLimitSwitch);
    private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.shooterLimitSwitch);
    private final double aimMotorSpeed = 0.5;

    public AimSubsystem() {
        super();
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

    public void setAim(int encoderPosition, int delta) {
        runAimMotor(0);
       encoderPosition = clampAimPosition(encoderPosition);
        if (isAtPosition(encoderPosition, delta)) {
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

    public boolean isAtPosition(int position, int delta) {
        position = clampAimPosition(position);
        if (aimMotorEncoder.get() == position) {
            return true;
        }
        if (aimMotorEncoder.get() <= position + delta &&
                aimMotorEncoder.get() >= position - delta) {
            return true;
        }
        return false;
    }

    private int clampAimPosition(int position){
        if (pickUpLimitSwitch.get()) {
           return  0;
        }
        if (shooterLimitSwitch.get()) {
            return aimMotorEncoder.get();
        }
        return position;
    }

}
