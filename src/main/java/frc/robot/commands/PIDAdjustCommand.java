package frc.robot.commands;

import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PIDAdjustCommand extends CommandBase {

    private CANPIDController pidController;
    // PID coefficients
    private double kP;
    private double kI;
    private double kD;
    private double kIz;
    private double kFF;
    private double kMaxOutput;
    private double kMinOutput;
    private double maxRPM;

    public PIDAdjustCommand(CANPIDController pidController, double kP, double kI, double kD,
                            double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM) {
        this.pidController = pidController;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kIz = kIz;
        this.kFF = kFF;
        this.kMaxOutput = kMaxOutput;
        this.kMinOutput = kMinOutput;
        this.maxRPM = maxRPM;
    }


}
