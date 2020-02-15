package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AimSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SetShooterPositionCommand extends CommandBase {
    private final AimSubsystem aimSubsystem;
    private final int position;
    private final int delta = 10;
    public SetShooterPositionCommand(AimSubsystem aimSubsystem, int position) {
        super();
        this.aimSubsystem = aimSubsystem;
        this.position = position;
        addRequirements(aimSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        aimSubsystem.setAim(position, delta);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        aimSubsystem.runAimMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return aimSubsystem.isAtPosition(position, delta);
    }
}
