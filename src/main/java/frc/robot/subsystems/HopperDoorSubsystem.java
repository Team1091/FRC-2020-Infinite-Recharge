package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperDoorSubsystem extends SubsystemBase {
    private Victor hopperReleaseMotor = new Victor(Constants.PWM.hopperReleaseMotor);
    private CANSparkMax hopperDoorMotor = new CANSparkMax(Constants.CAN.happerDoorMotor, CANSparkMaxLowLevel.MotorType.kBrushed);

    public HopperDoorSubsystem() {
        super();
        hopperDoorMotor.setInverted(Constants.hopperReleaseMotorInverted);
    }

    public void runHopperReleaseMotor(double speed) {
        hopperReleaseMotor.set(speed);
    }
    public void runHopperDoorMotor(double speed) {
        hopperDoorMotor.set(speed);
    }

}
