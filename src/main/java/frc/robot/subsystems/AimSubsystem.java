package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AimSubsystem extends SubsystemBase {

    private CANSparkMax aimMotor = new CANSparkMax(Constants.CAN.aimMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder aimMotorEncoder = new CANEncoder(aimMotor);
    private DigitalInput pickUpLimitSwitch = new DigitalInput(Constants.DIO.pickUpLimitSwitch);
    private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.DIO.shooterLimitSwitch);
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
            aimMotorEncoder.setPosition(0);
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
        if (aimMotorEncoder.getPosition() > encoderPosition) {
            runAimMotor(aimMotorSpeed);
            return;
        }
        if (aimMotorEncoder.getPosition() < encoderPosition) {
            runAimMotor(-aimMotorSpeed);
            return;
        }
    }

    public boolean isAtPosition(int position, int delta) {
        position = clampAimPosition(position);
        if (aimMotorEncoder.getPosition() == position) {
            return true;
        }
        if (aimMotorEncoder.getPosition() <= position + delta &&
                aimMotorEncoder.getPosition() >= position - delta) {
            return true;
        }
        return false;
    }

    private int clampAimPosition(int position){
        if (pickUpLimitSwitch.get()) {
           return  0;
        }
        if (shooterLimitSwitch.get()) {
            return (int)aimMotorEncoder.getPosition();
        }
        return position;
    }
    @Override
    public void periodic(){

    }
}
