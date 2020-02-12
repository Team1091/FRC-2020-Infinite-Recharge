package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class RunShooterMotorCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;
    private double power;

    public RunShooterMotorCommand(ShooterSubsystem shooterSubsystem, double velocity) {
        super();
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
        this.power = velocity;
    }

    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        this.shooterSubsystem.runShooter(power);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}