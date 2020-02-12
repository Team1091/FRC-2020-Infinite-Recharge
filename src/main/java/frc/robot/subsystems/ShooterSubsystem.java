package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax firstShooterMotor = new CANSparkMax(Constants.firstShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondShooterMotor = new CANSparkMax(Constants.secondShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder firstShooterEncoder = new CANEncoder(firstShooterMotor);
    private CANEncoder secondShooterEncoder = new CANEncoder(secondShooterMotor);

    public ShooterSubsystem() {
        super();
    }

    public void runShooter(double speed) {
        firstShooterMotor.set(speed);
        secondShooterMotor.set(speed);
    }
    public double getSpeed() {
        double percentOff = (secondShooterEncoder.getVelocity() / firstShooterEncoder.getVelocity());
        if(percentOff < 1.10 || percentOff > 0.90){
            return firstShooterEncoder.getVelocity();
        }
        else{
            return 0.0; //TODO: Figure out proper check for when motors do not match
        }
    }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("First Shooter Encoder", firstShooterEncoder.getVelocity());
        SmartDashboard.putNumber("Second Shooter Encoder", secondShooterEncoder.getVelocity());
    }
}
