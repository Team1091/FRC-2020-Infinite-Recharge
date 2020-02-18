package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
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
                        new RunShooterMotorCommand(shooterSubsystem, -0.5),
                        new RunHopperMotorCommand(hopperSubsystem, 0.5),
                        new RunPickUpMotorCommand(pickUpSubsystem, -0.5),
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
        return new SequentialCommandGroup(
                new ParallelRaceGroup(
                        new SetShooterPositionCommand(aimSubsystem, Constants.ShooterPositions.Shoot),
                        new RunShooterMotorCommand(shooterSubsystem, 1.0),
                        new DoWhileTrueCommand(booleanSupplier),
                        new TrackAmmoCommand(ammoCounterSubsystem, true)
                ),
                new ParallelRaceGroup(
                        new RunShooterMotorCommand(shooterSubsystem, 1.0),
                        new DoWhileTrueCommand(() -> shooterSubsystem.getSpeed() < 1000.0), //Velocity is measure in RPM
                        new DoWhileTrueCommand(booleanSupplier),
                        new TrackAmmoCommand(ammoCounterSubsystem, true)
                ),
                new ParallelRaceGroup(
                        new RunShooterMotorCommand(shooterSubsystem, 1.0),
                        new RunHopperMotorCommand(hopperSubsystem, 1.0),
                        new ReleaseAmmoCommand(electroMagSubsystem, 0.6),
                        new DoWhileTrueCommand(booleanSupplier),
                        new TrackAmmoCommand(ammoCounterSubsystem, true)
                )
        );
    }
}
