package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.util.AccelerationCurve;

public class DriveForwardsCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final double distanceToTravel;
    private AccelerationCurve speedCurve = new AccelerationCurve(4, .375, .8);

    public DriveForwardsCommand(DriveTrainSubsystem driveTrainSubsystem, double distanceToTravel) {
        super();
        this.distanceToTravel = distanceToTravel;
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
        speedCurve.set(-1);
        driveTrainSubsystem.doArcadeDrive(speedCurve.getCurrentSpeed(),0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.doArcadeDrive(0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.driveTrainSubsystem.getDistanceFeet() > distanceToTravel;
    }
}
