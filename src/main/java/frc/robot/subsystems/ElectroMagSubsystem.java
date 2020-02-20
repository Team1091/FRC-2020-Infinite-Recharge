package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElectroMagSubsystem extends SubsystemBase {
    private Victor hopperReleaseMotor = new Victor(Constants.PWM.hopperReleaseMotor);
    private Relay electroMagnetRelay = new Relay(Constants.RELAY.electroMag);

    public ElectroMagSubsystem() {
        super();
        hopperReleaseMotor.setInverted(Constants.hopperReleaseMotorInverted);
    }

    public void runHopperReleaseMotor(double speed) {
        hopperReleaseMotor.set(speed);
    }

    public void turnOnElectroMagnet() {
        electroMagnetRelay.set(Relay.Value.kOn);
    }

    public void turnOffElectroMagnet() {
        electroMagnetRelay.set(Relay.Value.kOff);
    }
}
