package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElectroMagSubsystem;

public class ReleaseAmmoCommand extends CommandBase {
    private ElectroMagSubsystem electroMagSubsystem;
    private double speed;

    public ReleaseAmmoCommand(ElectroMagSubsystem electroMagSubsystem, double speed){
        this.electroMagSubsystem = electroMagSubsystem;
        this.speed = speed;
    }
    @Override
    public void initialize() {
        electroMagSubsystem.turnOnElectroMagnet();
    }
    @Override
    public void execute() {
        electroMagSubsystem.runHopperReleaseMotor(speed);
    }
    @Override
    public void end(boolean interrupted){
        electroMagSubsystem.runHopperReleaseMotor(0);
        electroMagSubsystem.turnOffElectroMagnet();
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}
