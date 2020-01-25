package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PickUpSubsystem extends SubsystemBase {
    private Victor pickUpMotor = new Victor(1);

    public PickUpSubsystem() {
        super();

    }

    public void runPickUpMotor(double speed) {
        pickUpMotor.set(speed);
    }
}
