package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AimSubsystem;

public class RunAimMotorCommand extends CommandBase {
    private AimSubsystem aimSubsystem;
    private double power;

    public RunAimMotorCommand(AimSubsystem aimSubsystem, double velocity) {
        super();
        this.aimSubsystem = aimSubsystem;
        this.power = velocity;
        addRequirements(aimSubsystem);
    }

    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        this.aimSubsystem.runAimMotor(power);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        aimSubsystem.runAimMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}