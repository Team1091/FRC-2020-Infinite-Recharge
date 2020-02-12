/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int leftDriveMotor = 1; //CAN value
    public static final int rightDriveMotor = 2; //CAN value
    public static final int aimMotor = 2; //PWM value
    public static final int firstShooterMotor = 3; //CAN value
    public static final int secondShooterMotor = 4; //CAN value
    public static final int intakeMotor = 5;
    public static final int HopperMotor = 3;
    public static final int liftMotor = 6;

    public static final int leftEncoderA = 7;
    public static final int leftEncoderB = 8;
    public static final int rightEncoderA = 9;
    public static final int rightEncoderB = 10;
    public static final int aimEncoderA = 11;
    public static final int aimEncoderB = 12;
    public static final int shooterLimitSwitch = 1;
    public static final int pickUpLimitSwitch = 2;

    public static final int shooterEncoderA = 13;
    public static final int shooterEncoderB = 14;
}
