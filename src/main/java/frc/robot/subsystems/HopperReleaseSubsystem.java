package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperReleaseSubsystem extends SubsystemBase {
    private Victor hopperReleaseMotor = new Victor(Constants.PWM.hopperReleaseMotor);

    public HopperReleaseSubsystem() {
        super();
        hopperReleaseMotor.setInverted(Constants.hopperReleaseMotorInverted);
    }

    public void runHopperMotor(double speed) {
        hopperReleaseMotor.set(speed);
    }
}

