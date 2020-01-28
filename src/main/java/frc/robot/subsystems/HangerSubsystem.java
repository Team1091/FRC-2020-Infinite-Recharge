package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HangerSubsystem extends SubsystemBase {
    private Victor liftMotor = new Victor(Constants.liftMotor);

    public HangerSubsystem(){
        super();
    }

    public void up(){
        liftMotor.set(1);
    }

    public void down(){
        liftMotor.set(-1);
    }

    public void stop() {
        liftMotor.set(0);
    }
}
