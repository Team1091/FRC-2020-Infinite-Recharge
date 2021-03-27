package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperReleaseSubsystem;
import frc.robot.subsystems.HopperSubsystem;

public class RunHopperReleaseMotorCommand extends CommandBase {
    private HopperReleaseSubsystem hopperReleaseSubsystem;
    private double speed;

    public RunHopperReleaseMotorCommand(HopperReleaseSubsystem hopperReleaseSubsystem) {
        this(hopperReleaseSubsystem, 1.0);
    }

    public RunHopperReleaseMotorCommand(HopperReleaseSubsystem hopperReleaseSubsystem, double speed) {
        this.hopperReleaseSubsystem = hopperReleaseSubsystem;
        this.speed = speed;
        addRequirements(hopperReleaseSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hopperReleaseSubsystem.runHopperMotor(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hopperReleaseSubsystem.runHopperMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
