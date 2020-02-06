package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.PickUpSubsystem;

public class RunHopperMotorCommand extends CommandBase {
    private HopperSubsystem hopperSubsystem;
    private double speed;

    public RunHopperMotorCommand(HopperSubsystem hopperSubsystem) {
        this(hopperSubsystem, .5);
    }

    public RunHopperMotorCommand(HopperSubsystem hopperSubsystem, double speed) {
        this.hopperSubsystem = hopperSubsystem;
        this.speed = speed;
        addRequirements(hopperSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hopperSubsystem.runHopperMotor(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hopperSubsystem.runHopperMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
