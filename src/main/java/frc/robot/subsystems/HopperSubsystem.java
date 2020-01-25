package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {
    private Victor hopperMotor = new Victor(1);

    public HopperSubsystem() {
        super();
    }

    public void runHopperMotor(double speed) {
        hopperMotor.set(speed);
    }
}
