package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;

public class CommandFactory {
    public static CommandBase buildSuctionBallCommand(ShooterSubsystem shooterSubsystem,
                                                      AimSubsystem aimSubsystem,
                                                      HopperSubsystem hopperSubsystem,
                                                      PickUpSubsystem pickUpSubsystem,
                                                      AmmoCounterSubsystem ammoCounterSubsystem,
                                                      BooleanSupplier booleanSupplier) {
        return new SequentialCommandGroup(
                new SetShooterPositionCommand(aimSubsystem, Constants.ShooterPositions.Pickup),
                new ParallelRaceGroup(
                        new RunShooterMotorCommand(shooterSubsystem, 0.2),
                        new RunHopperMotorCommand(hopperSubsystem, 0.5),
                        new RunPickUpMotorCommand(pickUpSubsystem, 0.5),
                        new DoWhileTrueCommand(booleanSupplier),
                        new TrackAmmoCommand(ammoCounterSubsystem, false)
                )
        );

    }

    public static CommandBase buildShootBallCommand(ShooterSubsystem shooterSubsystem,
                                                    AimSubsystem aimSubsystem,
                                                    HopperSubsystem hopperSubsystem,
                                                    AmmoCounterSubsystem ammoCounterSubsystem,
                                                    ElectroMagSubsystem electroMagSubsystem,
                                                    BooleanSupplier booleanSupplier) {
        var shooterSpeed = 5000;
        return new SequentialCommandGroup(
                new ParallelRaceGroup(
                        new SetShooterPositionCommand(aimSubsystem, Constants.ShooterPositions.Shoot),
                        new RunPidShooterCommand(shooterSubsystem, -1, -1),
                        new TrackAmmoCommand(ammoCounterSubsystem, true),
                        new DoWhileTrueCommand(booleanSupplier)
                ),
                new ParallelRaceGroup(
                        new RunPidShooterCommand(shooterSubsystem, -1, -1),
                        new DoWhileTrueCommand(() -> shooterSubsystem.getSpeed() < shooterSpeed), //Velocity is measure in RPM
                        new TrackAmmoCommand(ammoCounterSubsystem, true),
                        new DoWhileTrueCommand(booleanSupplier)
                ),
                new ParallelRaceGroup(
                        new RunPidShooterCommand(shooterSubsystem, -1, -1),
                        new RunHopperMotorCommand(hopperSubsystem, 0.4),
                        new ReleaseAmmoCommand(electroMagSubsystem, shooterSubsystem, shooterSpeed,0.5),
                        new TrackAmmoCommand(ammoCounterSubsystem, true),
                        new DoWhileTrueCommand(booleanSupplier)

                )
        );
    }
}
