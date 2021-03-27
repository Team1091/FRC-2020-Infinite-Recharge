package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.util.ShooterPosition;

import java.util.function.BooleanSupplier;

public class CommandFactory {
    public static CommandBase buildSuctionBallCommand(ShooterSubsystem shooterSubsystem,
                                                      AimSubsystem aimSubsystem,
                                                      HopperSubsystem hopperSubsystem,
                                                      PickUpSubsystem pickUpSubsystem,
                                                      HopperDoorSubsystem hopperDoorSubsystem,
                                                      HopperReleaseSubsystem hopperReleaseSubsystem,
                                                      BooleanSupplier booleanSupplier) {
        return new SequentialCommandGroup(
                new SetShooterPositionCommand(aimSubsystem, ShooterPosition.PICKUP),
                new ParallelRaceGroup(
                        new RunShooterMotorCommand(shooterSubsystem, 0.2),
                        new RunHopperMotorCommand(hopperSubsystem, 0.5),
                        new RunHopperDoorMotorCommand(hopperDoorSubsystem, 0.5),
                        new RunHopperReleaseMotorCommand(hopperReleaseSubsystem,-11),
                        new RunPickUpMotorCommand(pickUpSubsystem, 0.5),
                        //new TrackAmmoCommand(ammoCounterSubsystem, false),
                        new DoWhileTrueCommand(booleanSupplier)
                )
        );

    }

    public static CommandBase buildShootBallCommand(ShooterSubsystem shooterSubsystem,
                                                    AimSubsystem aimSubsystem,
                                                    HopperSubsystem hopperSubsystem,
                                                    HopperDoorSubsystem hopperDoorSubsystem,
                                                    HopperReleaseSubsystem hopperReleaseSubsystem,
                                                    BooleanSupplier booleanSupplier) {
        return new SequentialCommandGroup(
                new ParallelRaceGroup(
                        new SetShooterPositionCommand(aimSubsystem, ShooterPosition.SHOOT),
                        new RunPidShooterCommand(shooterSubsystem, -1, -1),
//                        new TrackAmmoCommand(ammoCounterSubsystem, true),
                        new DoWhileTrueCommand(booleanSupplier)
                ),
                new ParallelRaceGroup(
                        new RunPidShooterCommand(shooterSubsystem, -1, -1),
                        new DoWhileTrueCommand(() -> shooterSubsystem.getSpeed() < Constants.shootReadySpeed), //Velocity is measure in RPM
                        // new TrackAmmoCommand(ammoCounterSubsystem, true),
                        new RunHopperReleaseMotorCommand(hopperReleaseSubsystem,1),
                        new DoWhileTrueCommand(booleanSupplier)
                ),
                new ParallelRaceGroup(
                        new RunPidShooterCommand(shooterSubsystem, -1, -1),
                        new RunHopperMotorCommand(hopperSubsystem, 0.4),
                        new ReleaseAmmoCommand(hopperDoorSubsystem, shooterSubsystem, Constants.shootReadySpeed,0.5),
                        // new TrackAmmoCommand(ammoCounterSubsystem, true),
                        new RunHopperReleaseMotorCommand(hopperReleaseSubsystem,1),
                        new DoWhileTrueCommand(booleanSupplier)
                )
        );
    }
}
