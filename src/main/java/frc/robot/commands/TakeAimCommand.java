package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.examples.gearsbot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TakeAimCommand extends CommandBase {
    //private double distance = VisionSubsystem.getDistanceToTarget();
    private double targetCoordX = 0;
    private VisionSubsystem visionSubsystem = new VisionSubsystem();
    private double rotate = 0;

    public TakeAimCommand(){
        super();

    }

    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        targetCoordX = visionSubsystem.getTargetCordX();
        //todo and fix
        //ArcadeDriveCommand(0, GetX(), DriveTrain());
    }

    public double GetX(){
        return  targetCoordX;
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
