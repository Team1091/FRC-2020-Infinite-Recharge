package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveForwardsCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;

    public DriveForwardsCommand(DriveTrainSubsystem driveTrainSubsystem) {
        super();
        this.driveTrainSubsystem = driveTrainSubsystem;
        addRequirements(driveTrainSubsystem);
    }
    @Override
    public void initialize() {
        driveTrainSubsystem.reset();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveTrainSubsystem.doArcadeDrive(1,0);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.driveTrainSubsystem.getDistance() > 1;
    }
}
