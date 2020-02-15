package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AmmoCounterSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;

public class ResetAmmoCommand extends CommandBase {

    private AmmoCounterSubsystem ammoCounterSubsystem;

    public ResetAmmoCommand(AmmoCounterSubsystem ammoCounterSubsystem){
        this.ammoCounterSubsystem = ammoCounterSubsystem;
        addRequirements(ammoCounterSubsystem);
    }

    // Called when the command is initially scheduled.
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
        ammoCounterSubsystem.reset();
        return true;
    }

}
