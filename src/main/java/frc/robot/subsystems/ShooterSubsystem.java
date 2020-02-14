package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax firstShooterMotor = new CANSparkMax(Constants.firstShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax secondShooterMotor = new CANSparkMax(Constants.secondShooterMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder firstShooterEncoder = new CANEncoder(firstShooterMotor);
    private CANEncoder secondShooterEncoder = new CANEncoder(secondShooterMotor);

    private CANPIDController firstShooterPIDControl = firstShooterMotor.getPIDController();
    private CANPIDController secondShooterPIDControl = firstShooterMotor.getPIDController();

    //PID Coefficients
    private double kP = 6e-5;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0.000015;
    private double kMaxOutput = 1;
    private double kMinOutput = -1;
    private double maxRPM = 5700; //Possibly need to change?


    public ShooterSubsystem() {
        super();
        //These need to be set only once
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);

        firstShooterPIDControl.setP(kP);
        firstShooterPIDControl.setI(kI);
        firstShooterPIDControl.setD(kD);
        firstShooterPIDControl.setIZone(kIz);
        firstShooterPIDControl.setFF(kFF);
        firstShooterPIDControl.setOutputRange(kMinOutput, kMaxOutput);

        secondShooterPIDControl.setP(kP);
        secondShooterPIDControl.setI(kI);
        secondShooterPIDControl.setD(kD);
        secondShooterPIDControl.setIZone(kIz);
        secondShooterPIDControl.setFF(kFF);
        secondShooterPIDControl.setOutputRange(kMinOutput, kMaxOutput);
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
            return 0.0; //TODO: Replace this check system with a PID loop
        }
    }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("First Shooter Encoder", firstShooterEncoder.getVelocity());
        SmartDashboard.putNumber("Second Shooter Encoder", secondShooterEncoder.getVelocity());

    }
}