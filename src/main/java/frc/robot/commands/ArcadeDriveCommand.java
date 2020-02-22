package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.util.AccelerationCurve;

import java.util.function.DoubleSupplier;

public class ArcadeDriveCommand extends CommandBase {

    private DriveTrainSubsystem driveTrain;
    private DoubleSupplier speed;
    private DoubleSupplier turn;
    private final AccelerationCurve speedCurve = new AccelerationCurve(4, .375, .8);
    private final AccelerationCurve rotationCurve = new AccelerationCurve(4, .375, .8);

    public ArcadeDriveCommand(DriveTrainSubsystem driveTrain, DoubleSupplier speed, DoubleSupplier turn){
        this.driveTrain = driveTrain;
        this.speed = speed;
        this.turn = turn;


        addRequirements(driveTrain);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        speedCurve.set(speed.getAsDouble());
        rotationCurve.set(turn.getAsDouble());
        driveTrain.doArcadeDrive(speedCurve.getCurrentSpeed(),rotationCurve.getCurrentSpeed());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.doArcadeDrive(0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
