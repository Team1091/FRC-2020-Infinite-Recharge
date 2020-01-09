package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {

    private final int leftMotorChannel = 3;
    private final int rightMotorChannel = 2;

    private Victor leftMotor = new Victor(leftMotorChannel);
    private Victor rightMotor = new Victor(rightMotorChannel);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor,rightMotor);


    public DriveTrainSubsystem(){
        super();
    }


    public void doArcadeDrive(double speed, double rotation){
        drive.arcadeDrive(speed, rotation);
    }
}
