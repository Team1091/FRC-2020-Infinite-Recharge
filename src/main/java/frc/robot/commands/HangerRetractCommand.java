package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerSubsystem;

public class HangerRetractCommand extends CommandBase {
    private final HangerSubsystem hangerSubsystem;

    public HangerRetractCommand(HangerSubsystem hangerSubsystem) {
        super();
        this.hangerSubsystem = hangerSubsystem;
        addRequirements(hangerSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled. ywr
    @Override
    public void execute() {
        hangerSubsystem.down();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hangerSubsystem.stop();
    }

    // todo implement Make sure to make it so that when the robot is finishing with the game, then it spits
    // out all of its balls

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
