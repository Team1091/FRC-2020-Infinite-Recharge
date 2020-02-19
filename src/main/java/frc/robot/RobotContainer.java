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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.autonomous.DriveForwardsCommand;
import frc.robot.commands.autonomous.TurnCommand;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final XboxController xbox = new XboxController(0);

    private final VisionSubsystem visionSystem = new VisionSubsystem();
    private final DriveTrainSubsystem drivetrain = new DriveTrainSubsystem();

    private double kP = 6e-5;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0.000015;
    private double kMaxOutput = 1;
    private double kMinOutput = -1;
    private double maxRPM = 5700;
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
//    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem(
//            Constants.CAN.firstShooterMotor,
//            Constants.CAN.firstShooterMotor,
//            0.5,
//            kP, //how far away you are from the thing its multiplied into yo
//            kI, //integral, how fast are you going
//            kD, //derivative
//            kIz, //idk what this is, tuning to correct frequency
//            kFF, //kentucky fried fingers, also could be something with frequency
//            kMaxOutput,
//            kMinOutput,
//            maxRPM
//    );
    private final AimSubsystem aimSubsystem = new AimSubsystem();
    private final HangerSubsystem hangerSubsystem = new HangerSubsystem();
    private final PickUpSubsystem pickUpSubsystem = new PickUpSubsystem();
    private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
    private final AmmoCounterSubsystem ammoCounterSubsystem = new AmmoCounterSubsystem();
    private final ElectroMagSubsystem electroMagSubsystem = new ElectroMagSubsystem();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        drivetrain.setDefaultCommand(new ArcadeDriveCommand(
                drivetrain,
                () -> (xbox.getY(GenericHID.Hand.kLeft)),
                () -> (xbox.getX(GenericHID.Hand.kLeft))));

        hangerSubsystem.setDefaultCommand(new ControlHangerCommand(
                hangerSubsystem,
                ()-> xbox.getY(GenericHID.Hand.kRight)));
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand(drivetrain));
        SmartDashboard.putData("Reset Ammo", new ResetAmmoCommand(ammoCounterSubsystem));
        var leftbumper = new JoystickButton(xbox, XboxController.Button.kBumperLeft.value);
        leftbumper.whileActiveOnce(CommandFactory.buildSuctionBallCommand(
                shooterSubsystem, aimSubsystem, hopperSubsystem, pickUpSubsystem, ammoCounterSubsystem,
                () -> xbox.getBumper(GenericHID.Hand.kLeft)
        ));

        var rightbumper = new JoystickButton(xbox, XboxController.Button.kBumperRight.value);
        rightbumper.whileActiveOnce(CommandFactory.buildShootBallCommand(
                shooterSubsystem, aimSubsystem, hopperSubsystem, ammoCounterSubsystem, electroMagSubsystem,
                () -> xbox.getBumper(GenericHID.Hand.kRight)
        ));

        var yButton = new JoystickButton(xbox, XboxController.Button.kY.value);
        yButton.whileActiveOnce(new RunPickUpMotorCommand(pickUpSubsystem, -5));

        var bButton = new JoystickButton(xbox, XboxController.Button.kB.value);
        bButton.whileActiveOnce(new BoostedArcadeDriveCommand(
                () -> (xbox.getY(GenericHID.Hand.kLeft)),
                () -> (xbox.getX(GenericHID.Hand.kLeft)),
                drivetrain));
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
                new DriveForwardsCommand(drivetrain, 2.5),
                new TurnCommand(drivetrain, -90.0),
                new DriveForwardsCommand(drivetrain, 5.0),
                new TurnCommand(drivetrain, -90.0),
                CommandFactory.buildShootBallCommand(shooterSubsystem, aimSubsystem, hopperSubsystem, ammoCounterSubsystem, electroMagSubsystem, ()->true)
                //todo CHANGE THIS

        );
    }
}
