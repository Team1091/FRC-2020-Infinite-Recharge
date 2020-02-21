package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.util.AccelerationCurve;

public class TurnCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final double turnDistance;
    private AccelerationCurve turnCurve = new AccelerationCurve(4, .375, .8);
    private final double rotationFactor = 1.0;

    public TurnCommand(DriveTrainSubsystem driveTrainSubsystem, double clockwiseRotation) {
        super();
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.turnDistance = clockwiseRotation * rotationFactor;
        addRequirements(driveTrainSubsystem);
    }

    @Override
    public void initialize() {
        driveTrainSubsystem.reset();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (turnDistance < 0) {
            driveTrainSubsystem.setMotorSpeed(-0.7, 0.7); //add acceleration curve if time prevalent
        }
        if (turnDistance > 0) {
            driveTrainSubsystem.setMotorSpeed(0.7, -0.7); //add acceleration curve if time prevalent
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.setMotorSpeed(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        SmartDashboard.putNumber("Rotation", driveTrainSubsystem.getRotation());
        if (turnDistance > 0) {
            if (driveTrainSubsystem.getRotation() >= turnDistance) {
                return true;
            }
        } else if (turnDistance < 0) {
            if (driveTrainSubsystem.getRotation() <= turnDistance) {
                return true;
            }
        }

        return false;
    }
}
