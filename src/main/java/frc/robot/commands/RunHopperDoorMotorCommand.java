package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperDoorSubsystem;
import frc.robot.subsystems.HopperSubsystem;

public class RunHopperDoorMotorCommand extends CommandBase {
    private HopperDoorSubsystem hopperDoorSubsystem;
    private double speed;

    public RunHopperDoorMotorCommand(HopperDoorSubsystem hopperDoorSubsystem) {
        this(hopperDoorSubsystem, .5);
    }

    public RunHopperDoorMotorCommand(HopperDoorSubsystem hopperDoorSubsystem, double speed) {
        this.hopperDoorSubsystem = hopperDoorSubsystem;
        this.speed = speed;
        addRequirements(hopperDoorSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hopperDoorSubsystem.runHopperDoorMotor(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hopperDoorSubsystem.runHopperDoorMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
