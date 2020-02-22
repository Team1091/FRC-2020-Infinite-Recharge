package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PickUpSubsystem;

public class GrabABallCommand extends CommandBase {
    private final PickUpSubsystem pickUpSubsystem;

    public GrabABallCommand(PickUpSubsystem pickUpSubsystem) {
        super();
        this.pickUpSubsystem = pickUpSubsystem;
        addRequirements(pickUpSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        pickUpSubsystem.runPickUpMotor(1.0);
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
