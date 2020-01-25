package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TurnCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
   private final double degrees;

    public TurnCommand(DriveTrainSubsystem driveTrainSubsystem, double clockwiseRotation){
        super();
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.degrees = clockwiseRotation;
        addRequirements(driveTrainSubsystem);


    }
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
