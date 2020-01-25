package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class SetShooterPositionCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final int position;
    private final int delta = 10;
    public SetShooterPositionCommand(ShooterSubsystem shooterSubsystem, int position) {
        super();
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
        this.position = position;
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        shooterSubsystem.setAim(position, delta);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.runAimMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return shooterSubsystem.isAtPosition(position, delta);
    }
}
