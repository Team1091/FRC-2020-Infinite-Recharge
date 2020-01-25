/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final VisionSubsystem visionSystem = new VisionSubsystem();
    private final DriveTrainSubsystem drivetrain = new DriveTrainSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final HangerSubsystem hangerSubsystem = new HangerSubsystem();
    private final PickUpSubsystem pickUpSubsystem = new PickUpSubsystem();
    private final XboxController xbox = new XboxController(0);

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        drivetrain.setDefaultCommand(new ArcadeDriveCommand(
                () -> (xbox.getY(GenericHID.Hand.kLeft)),
                () -> (xbox.getX(GenericHID.Hand.kLeft)),
                drivetrain));

    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new SequentialCommandGroup(
                new DriveForwardsCommand(drivetrain, 5.0),
                new TurnCommand(drivetrain, 180.0),
                new ShootABallCommand(shooterSubsystem, 1)

        );
    }
}
