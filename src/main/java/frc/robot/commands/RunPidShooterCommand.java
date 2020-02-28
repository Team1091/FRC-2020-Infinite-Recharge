package frc.robot.commands;

import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RunPidShooterCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;
    private VisionSubsystem visionSubsystem;

    private double leftSpeed;
    private double rightSpeed;

    private CANPIDController leftPidController;
    private CANPIDController rightPidController;

    public RunPidShooterCommand(ShooterSubsystem shooterSubsystem, double leftSpeed, double rightSpeed) {
        this(shooterSubsystem);
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
    }

    public RunPidShooterCommand(ShooterSubsystem shooterSubsystem, VisionSubsystem visionSubsystem) {
        this(shooterSubsystem);
        this.visionSubsystem = visionSubsystem;
    }

    private RunPidShooterCommand(ShooterSubsystem shooterSubsystem) {
        super();
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);

        leftPidController = shooterSubsystem.getPIDLeftController();
        rightPidController = shooterSubsystem.getPIDRightController();
    }

    @Override
    public void initialize() {

        leftPidController.setP(Constants.SparkMaxNeoTurningDefaults.kP);
        leftPidController.setI(Constants.SparkMaxNeoTurningDefaults.kI);
        leftPidController.setD(Constants.SparkMaxNeoTurningDefaults.kD);
        leftPidController.setIZone(Constants.SparkMaxNeoTurningDefaults.kIz);
        leftPidController.setFF(Constants.SparkMaxNeoTurningDefaults.kFF);
        leftPidController.setOutputRange(Constants.SparkMaxNeoTurningDefaults.kMinOutput, Constants.SparkMaxNeoTurningDefaults.kMaxOutput);

        rightPidController.setP(Constants.SparkMaxNeoTurningDefaults.kP);
        rightPidController.setI(Constants.SparkMaxNeoTurningDefaults.kI);
        rightPidController.setD(Constants.SparkMaxNeoTurningDefaults.kD);
        rightPidController.setIZone(Constants.SparkMaxNeoTurningDefaults.kIz);
        rightPidController.setFF(Constants.SparkMaxNeoTurningDefaults.kFF);
        rightPidController.setOutputRange(Constants.SparkMaxNeoTurningDefaults.kMinOutput, Constants.SparkMaxNeoTurningDefaults.kMaxOutput);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (visionSubsystem != null) {
            //Re-calculate Speed Based on vision data to sick curves. 
        }

        leftPidController.setReference(leftSpeed * Constants.SparkMaxNeoTurningDefaults.maxRPM, ControlType.kVelocity);
        rightPidController.setReference(rightSpeed * Constants.SparkMaxNeoTurningDefaults.maxRPM, ControlType.kVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        leftPidController.setReference(0, ControlType.kVelocity);
        rightPidController.setReference(0, ControlType.kVelocity);
        shooterSubsystem.runShooter(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
