package frc.robot.subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {
    private Victor hopperMotor = new Victor(Constants.PWM.HopperMotor);
    public HopperSubsystem(){
        super();
    }

    public void runHopperMotor(double speed) {
        hopperMotor.set(speed);
    }

    @Override
    public void periodic(){
        //hopperMotor.set(1);
    }
}

