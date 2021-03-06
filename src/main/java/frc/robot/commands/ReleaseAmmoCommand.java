package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperDoorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ReleaseAmmoCommand extends CommandBase {
    private HopperDoorSubsystem hopperDoorSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private double speed;
    private double shootSpeed;

    public ReleaseAmmoCommand(HopperDoorSubsystem hopperDoorSubsystem,
                              ShooterSubsystem shooterSubsystem,
                              double shootSpeed,
                              double speed) {
        this.hopperDoorSubsystem = hopperDoorSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.speed = speed;
        this.shootSpeed = shootSpeed;
        addRequirements(hopperDoorSubsystem);
    }

    @Override
    public void initialize() {
        hopperDoorSubsystem.runHopperDoorMotor(0);
    }

    @Override
    public void execute() {
//        if(shooterSubsystem.getSpeed() > shootSpeed){
            hopperDoorSubsystem.runHopperReleaseMotor(speed);
//        } else {
//            electroMagSubsystem.runHopperReleaseMotor(0);
//        }
    }

    @Override
    public void end(boolean interrupted) {
        hopperDoorSubsystem.runHopperReleaseMotor(0);
        hopperDoorSubsystem.runHopperDoorMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
