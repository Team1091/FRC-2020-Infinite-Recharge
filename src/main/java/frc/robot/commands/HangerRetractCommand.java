package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerSubsystem;
import frc.robot.commands.RunHopperMotorCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class HangerRetractCommand extends CommandBase {
    private final HangerSubsystem hangerSubsystem;
    private ShooterSubsystem Shooter = new ShooterSubsystem();
    private double velocity = 0.4;

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

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
