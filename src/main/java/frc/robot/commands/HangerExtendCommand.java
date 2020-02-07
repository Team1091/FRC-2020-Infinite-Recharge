package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerSubsystem;

public class HangerExtendCommand extends CommandBase {
    private final HangerSubsystem hangerSubsystem;

    public HangerExtendCommand(HangerSubsystem hangerSubsystem) {
        super();
        this.hangerSubsystem = hangerSubsystem;
        addRequirements(hangerSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hangerSubsystem.up();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hangerSubsystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
