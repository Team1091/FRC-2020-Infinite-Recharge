package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PickUpSubsystem extends SubsystemBase {
    private Victor intakeMotor = new Victor(Constants.PWM.intakeMotor);

    public PickUpSubsystem() {
        super();
        intakeMotor.setInverted(Constants.intakeMotorInverted);
    }

    public void runPickUpMotor(double speed) {
        intakeMotor.set(speed);
    }
}
