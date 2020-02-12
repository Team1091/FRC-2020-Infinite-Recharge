package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PickUpSubsystem extends SubsystemBase {
    private Victor pickUpMotor = new Victor(Constants.intakeMotor);

    public PickUpSubsystem() {
        super();
    }

    public void runPickUpMotor(double speed) {
        pickUpMotor.set(speed);
    }
}
