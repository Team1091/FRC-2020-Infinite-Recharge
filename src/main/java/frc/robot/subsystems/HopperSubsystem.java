package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {
    private Victor hopperMotor = new Victor(Constants.PWM.HopperMotor);

    public HopperSubsystem() {
        super();
        hopperMotor.setInverted(Constants.hopperMotorInverted);
    }

    public void runHopperMotor(double speed) {
        hopperMotor.set(speed);
    }

    @Override
    public void periodic() {
        //hopperMotor.set(1);
    }
}

