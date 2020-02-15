package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HangerSubsystem extends SubsystemBase {
    private Victor liftMotor = new Victor(Constants.PWM.liftMotor);

    public HangerSubsystem(){
        super();
    }

    public void runLiftMotor(double speed){
        liftMotor.set(speed);
    }
}
