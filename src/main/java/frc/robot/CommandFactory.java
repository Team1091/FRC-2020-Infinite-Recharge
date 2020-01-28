package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.PickUpSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.BooleanSupplier;

public class CommandFactory {
    public CommandBase buildSuctionBallCommand(ShooterSubsystem shooterSubsystem,
                                               HopperSubsystem hopperSubsystem,
                                               PickUpSubsystem pickUpSubsystem,
                                               BooleanSupplier booleanSupplier){
        return new SequentialCommandGroup(
                new SetShooterPositionCommand(shooterSubsystem,0),
                new ParallelRaceGroup(
                        new RunShooterMotorCommand(shooterSubsystem,-0.5),
                        new RunHopperMotorCommand(hopperSubsystem, -0.5),
                        new RunPickUpMotorCommand(pickUpSubsystem,-0.5),
                        new DoWhileTrueCommand(booleanSupplier)
                )
        );
        //todo:SToP
    }
}
