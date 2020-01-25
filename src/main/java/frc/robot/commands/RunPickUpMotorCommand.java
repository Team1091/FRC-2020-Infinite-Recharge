package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PickUpSubsystem;

public class RunPickUpMotorCommand extends CommandBase {
    private PickUpSubsystem pickUpSubsystem;
    private double speed;

    public RunPickUpMotorCommand(PickUpSubsystem pickUpSubsystem) {
        this(pickUpSubsystem, .5);
    }

    public RunPickUpMotorCommand(PickUpSubsystem pickUpSubsystem, double speed) {
        this.pickUpSubsystem = pickUpSubsystem;
        this.speed = speed;
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        pickUpSubsystem.runPickUpMotor(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        pickUpSubsystem.runPickUpMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
