package frc.robot.commands.pidTuning;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PIDTunableSubsystem;
import frc.robot.util.PIDTuner;

public class PidTuningCommand extends CommandBase {
    private PIDTuner[] tuners;

    public PidTuningCommand(PIDTunableSubsystem subsystem) {
        tuners = subsystem.getPidTuners();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        for(var tuner : tuners){
            tuner.init();
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        for(var tuner : tuners){
            tuner.run();
        }
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
