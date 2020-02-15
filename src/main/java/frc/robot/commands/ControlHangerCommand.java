package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerSubsystem;

import java.util.function.DoubleSupplier;

public class ControlHangerCommand extends CommandBase {
    private final HangerSubsystem hangerSubsystem;
    private DoubleSupplier stickValue;

    public ControlHangerCommand(HangerSubsystem hangerSubsystem, DoubleSupplier stickValue) {
        super();
        this.hangerSubsystem = hangerSubsystem;
        this.stickValue = stickValue;
        addRequirements(hangerSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hangerSubsystem.runLiftMotor(stickValue.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hangerSubsystem.runLiftMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
