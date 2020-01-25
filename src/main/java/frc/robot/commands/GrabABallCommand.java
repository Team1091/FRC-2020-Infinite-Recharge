package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerSubsystem;
import frc.robot.subsystems.PickUpSubsystem;

public class GrabABallCommand extends CommandBase {
    private final PickUpSubsystem pickUpSubsystem;

    public GrabABallCommand(PickUpSubsystem pickUpSubsystem) {
        super();
        this.pickUpSubsystem = pickUpSubsystem;
        addRequirements(pickUpSubsystem);


    }

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
        return false;
    }

    public static class HangerCommand extends CommandBase {
        private final HangerSubsystem hangerSubsystem;

        public HangerCommand(HangerSubsystem hangerSubsystem) {

            super();
            this.hangerSubsystem = hangerSubsystem;
            addRequirements(hangerSubsystem);

        }

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
            return false;
        }
    }
}
