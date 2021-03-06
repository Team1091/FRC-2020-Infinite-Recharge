package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.util.AccelerationCurve;
import frc.robot.util.TargetCoordinate;

public class TakeAimCommand extends CommandBase {
    private VisionSubsystem visionSubsystem;
    private DriveTrainSubsystem driveTrainSubsystem;
    private final double rotationalDelta = .25;
    private final double desiredDistance = 8.5;
    private final double desiredDistanceDelta = .3;
    private AccelerationCurve speedCurve = new AccelerationCurve(4, .375, .6);
    private AccelerationCurve turnCurve = new AccelerationCurve(4, .375, .8);

    public TakeAimCommand(VisionSubsystem visionSubsystem, DriveTrainSubsystem driveTrainSubsystem) {
        super();
        this.visionSubsystem = visionSubsystem;
        this.driveTrainSubsystem = driveTrainSubsystem;

        addRequirements(visionSubsystem);
        addRequirements(driveTrainSubsystem);
    }

    @Override
    public void initialize() {
    }

    private boolean targetCloseEnough() {
        var distance = visionSubsystem.getDistanceToTarget();
        if (distance == null ) return false;
        return Math.abs(distance - desiredDistance) < desiredDistanceDelta;
    }


    private double getTurn(TargetCoordinate targetCoordinate) {
        if(targetCoordinate == null){
            return 0;
        }
        var X = targetCoordinate.getX();
        if (X < -rotationalDelta) {
            turnCurve.set(-1);
            return turnCurve.getCurrentSpeed();
        }
        if (X > rotationalDelta) {
            turnCurve.set(1);
            return turnCurve.getCurrentSpeed();
        }
        return 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        var targetCoordinate = visionSubsystem.getTargetCoordinates();
        //1 can we see the target?
        if (targetCoordinate == null) {
            return;
        }
        //2 find out if we can shoot
        if (targetCloseEnough()) {
            return;
        }
        //3 if we can see it then do we need to turn
        var turn = getTurn(targetCoordinate);
        //4 if we don't need to turn then how far forwards do we go
        var move = getMove();
        //5 once we have this get this info to the arcade drive
        driveTrainSubsystem.doArcadeDrive(move, turn);
    }

    public double getMove() {
        //1 calculate distance between desierd position and robot
        var distance = visionSubsystem.getDistanceToTarget();
        if (distance == null){
            return 0;
        }
        var desiredTravel = distance - desiredDistance;
        //2 if to far away move forward
        if (desiredTravel > desiredDistanceDelta) {
            speedCurve.set(-1);
            return speedCurve.getCurrentSpeed();
        }
        //3 if too close move backward
        if (desiredTravel < -desiredDistanceDelta) {
            speedCurve.set(1);
            return speedCurve.getCurrentSpeed();
        }

        return 0.0;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.doArcadeDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return targetCloseEnough();
    }
}
