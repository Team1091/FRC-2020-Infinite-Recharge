package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperDoorSubsystem extends SubsystemBase {
    private Victor hopperReleaseMotor = new Victor(Constants.PWM.hopperReleaseMotor);

    public HopperDoorSubsystem() {
        super();
        hopperReleaseMotor.setInverted(Constants.hopperReleaseMotorInverted);
    }

    public void runHopperReleaseMotor(double speed) {
        hopperReleaseMotor.set(speed);
    }
    public void runHopperDoorMotor(double speed) {
        hopperReleaseMotor.set(speed);
    }

}
