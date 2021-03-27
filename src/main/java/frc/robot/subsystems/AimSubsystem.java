package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AimSubsystem extends SubsystemBase {
    // TODO: this needs to be pwm, no encoder on it now
    private Victor aimMotor = new Victor(Constants.PWM.aimMotor);

    //private CANEncoder aimMotorEncoder = new CANEncoder(aimMotor);
    private DigitalInput pickUpLimitSwitch = new DigitalInput(Constants.DIO.pickUpLimitSwitch);
    private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.DIO.shooterLimitSwitch);

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
        runAimMotor(Constants.aimMotorSpeed);
    }

    public void goToPickupPosition() {
        if (pickUpLimitSwitch.get()) {
            return;
        }
        runAimMotor(-Constants.aimMotorSpeed);
    }

    public boolean isInShootPosition() {
        return shooterLimitSwitch.get();
    }

    public boolean isAtPickUpPosition() {
        return pickUpLimitSwitch.get();
    }


    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Shooter limit switch", shooterLimitSwitch.get());
        SmartDashboard.putBoolean("Pickup limit switch", pickUpLimitSwitch.get());
    }
}
