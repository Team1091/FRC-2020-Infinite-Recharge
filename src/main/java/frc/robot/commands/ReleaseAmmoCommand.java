package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElectroMagSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ReleaseAmmoCommand extends CommandBase {
    private ElectroMagSubsystem electroMagSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private double speed;
    private double shootSpeed;

    public ReleaseAmmoCommand(ElectroMagSubsystem electroMagSubsystem,
                              ShooterSubsystem shooterSubsystem,
                              double shootSpeed,
                              double speed) {
        this.electroMagSubsystem = electroMagSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.speed = speed;
        this.shootSpeed = shootSpeed;
        addRequirements(electroMagSubsystem);
    }

    @Override
    public void initialize() {
        electroMagSubsystem.turnOnElectroMagnet();
    }

    @Override
    public void execute() {
//        if(shooterSubsystem.getSpeed() > shootSpeed){
            electroMagSubsystem.runHopperReleaseMotor(speed);
//        } else {
//            electroMagSubsystem.runHopperReleaseMotor(0);
//        }
    }

    @Override
    public void end(boolean interrupted) {
        electroMagSubsystem.runHopperReleaseMotor(0);
        electroMagSubsystem.turnOffElectroMagnet();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
