/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.autonomous.TurnCommand;
import frc.robot.commands.pidTuning.PidTuningCommand;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final XboxController xbox = new XboxController(0);

    private final VisionSubsystem visionSystem = new VisionSubsystem();
    private final DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();
    private final HopperReleaseSubsystem hopperReleaseSubsystem = new HopperReleaseSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final AimSubsystem aimSubsystem = new AimSubsystem();
    private final PickUpSubsystem pickUpSubsystem = new PickUpSubsystem();
    private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
    // private final AmmoCounterSubsystem ammoCounterSubsystem = new AmmoCounterSubsystem();
    private final HopperDoorSubsystem hopperDoorSubsystem = new HopperDoorSubsystem();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        driveTrain.setDefaultCommand(new ArcadeDriveCommand(
                driveTrain,
                () -> (xbox.getY(GenericHID.Hand.kLeft)),
                () -> (xbox.getX(GenericHID.Hand.kLeft))));


    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand(driveTrain));
        // SmartDashboard.putData("Reset Ammo", new SetAmmoCommand(ammoCounterSubsystem, 0));
        // SmartDashboard.putData("Increment Ammo", new SetAmmoCommand(ammoCounterSubsystem, ammoCounterSubsystem.getAmmo() + 1));
        // SmartDashboard.putData("Decrement Ammo", new SetAmmoCommand(ammoCounterSubsystem, ammoCounterSubsystem.getAmmo() - 1));
        // SmartDashboard.putData("Reset Ammo", new SetAmmoCommand(ammoCounterSubsystem, 0));
        // SmartDashboard.putData("Run Drive Pid Tuning", new PidTuningCommand(driveTrain));
        // SmartDashboard.putData("Run Shooter Pid Tuning", new PidTuningCommand(shooterSubsystem));

        // Test command
        var testButton = new JoystickButton(xbox, XboxController.Button.kStart.value);
        testButton.whileActiveOnce(new TakeAimCommand(visionSystem, driveTrain));

        //Robot Suck
        var leftBumper = new JoystickButton(xbox, XboxController.Button.kBumperLeft.value);
        leftBumper.whileActiveOnce(CommandFactory.buildSuctionBallCommand(
                shooterSubsystem, aimSubsystem, hopperSubsystem, pickUpSubsystem,
                hopperDoorSubsystem, hopperReleaseSubsystem,
                () -> xbox.getBumper(GenericHID.Hand.kLeft)
        ));

        //Robot Shoot
        var rightBumper = new JoystickButton(xbox, XboxController.Button.kBumperRight.value);
        rightBumper.whileActiveOnce(CommandFactory.buildShootBallCommand(
                shooterSubsystem, aimSubsystem, hopperSubsystem, hopperDoorSubsystem, hopperReleaseSubsystem,
                () -> xbox.getBumper(GenericHID.Hand.kRight)
        ));

        //Push balls
        var yButton = new JoystickButton(xbox, XboxController.Button.kY.value);
        yButton.whileActiveOnce(new RunPickUpMotorCommand(pickUpSubsystem, -5));

        var xButton = new JoystickButton(xbox, XboxController.Button.kX.value);
        xButton.whileActiveOnce(new RunAimMotorCommand(aimSubsystem, .5));

        var aButton = new JoystickButton(xbox, XboxController.Button.kA.value);
        aButton.whileActiveOnce(new RunAimMotorCommand(aimSubsystem, -.5));

        var backButton = new JoystickButton(xbox, XboxController.Button.kBack.value);
        backButton.whileActiveOnce(new RunHopperDoorMotorCommand(hopperDoorSubsystem, .5));

        var startButton = new JoystickButton(xbox, XboxController.Button.kStart.value);
        startButton.whileActiveOnce(new RunHopperDoorMotorCommand(hopperDoorSubsystem, -.5));


        //NITRO
        var bButton = new JoystickButton(xbox, XboxController.Button.kB.value);
        bButton.whileActiveOnce(new BoostedArcadeDriveCommand(
                () -> (xbox.getY(GenericHID.Hand.kLeft)),
                () -> (xbox.getX(GenericHID.Hand.kLeft)),
                driveTrain));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // return new ExampleCommand(new ExampleSubsystem());
        return new SequentialCommandGroup(
                //new DriveForwardsCommand(driveTrain, 2.5),
                new TurnCommand(driveTrain, 180.0),
                //new DriveForwardsCommand(driveTrain, 5.0),
                //new TurnCommand(drivetrain, -90.0),
                new TakeAimCommand(visionSystem, driveTrain),
                CommandFactory.buildShootBallCommand(shooterSubsystem, aimSubsystem, hopperSubsystem, hopperDoorSubsystem, hopperReleaseSubsystem, () -> true)
                //todo Make robot not fly backwards

        );
    }
}
