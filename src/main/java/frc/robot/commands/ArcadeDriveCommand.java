package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

import java.util.function.DoubleSupplier;

public class ArcadeDriveCommand extends CommandBase {

    private DriveTrainSubsystem drivetrain;
    private DoubleSupplier speed;
    private DoubleSupplier turn;

    public ArcadeDriveCommand(DoubleSupplier speed, DoubleSupplier turn, DriveTrainSubsystem drivetrain){
        this.speed = speed;
        this.turn = turn;
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.doArcadeDrive(speed.getAsDouble(),turn.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.doArcadeDrive(0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
