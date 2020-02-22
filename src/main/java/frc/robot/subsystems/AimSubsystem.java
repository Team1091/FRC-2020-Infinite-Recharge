package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AimSubsystem extends SubsystemBase {

    private CANSparkMax aimMotor = new CANSparkMax(Constants.CAN.aimMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder aimMotorEncoder = new CANEncoder(aimMotor);
    private DigitalInput pickUpLimitSwitch = new DigitalInput(Constants.DIO.pickUpLimitSwitch);
    private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.DIO.shooterLimitSwitch);
    private final double aimMotorSpeed = 1.0;

    public AimSubsystem() {
        super();
        aimMotor.setInverted(Constants.aimMotorInverted);
    }


    public void runAimMotor(double speed) {
        aimMotor.set(speed);
    }

    public void goToShootPosition() {
        if (shooterLimitSwitch.get()) {
            return;
        }
        runAimMotor(aimMotorSpeed);
    }

    public void goToPickupPosition() {
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
        return aimMotorEncoder.getPosition() <= position + delta &&
                aimMotorEncoder.getPosition() >= position - delta;
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
        SmartDashboard.putBoolean("Shooter limit switch",shooterLimitSwitch.get());
        SmartDashboard.putBoolean("Pickup limit switch",pickUpLimitSwitch.get());
        SmartDashboard.putNumber("Aim Encoder", aimMotorEncoder.getPosition());
    }
}
