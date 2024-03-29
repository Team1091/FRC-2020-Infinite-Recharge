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
    public static final boolean intakeMotorInverted = false;
    public static final boolean hopperMotorInverted = true;
    public static final boolean liftMotorInverted = false;
    public static final boolean hopperDoorMotorInverted = true;
    public static final boolean hopperReleaseMotorInverted = false;
    public static final boolean rightDriveMotorInverted = false;
    public static final boolean leftDriveMotorInverted = false;
    public static final boolean aimMotorInverted = false;
    public static final boolean rightShooterMotorInverted = true;
    public static final boolean leftShooterMotorInverted = false;
    public static final double aimMotorSpeed = 0.6;
    public static final double shootReadySpeed = 4000;


    public static final double encoderToFootRatio = 4.16424275; // this many encoder ticks is 1 foot

    public static final class PWM {
        public static final int intakeMotor = 5;
        public static final int HopperMotor = 3;
        public static final int aimMotor = 6;
        public static final int hopperDoorMotor = 1;
        public static final int hopperReleaseMotor = 0;
    }

    public static final class CAN {
        public static final int firstRightDriveMotor = 2;
        public static final int secondRightDriveMotor = 3;
        public static final int firstLeftDriveMotor = 7;
        public static final int secondLeftDriveMotor = 8;
        public static final int leftShooterMotor = 9;
        public static final int rightShooterMotor = 10;
    }

    public static final class DIO {
        public static final int shooterLimitSwitch = 1;
        public static final int pickUpLimitSwitch = 0;
    }

    public static final class SparkMaxNeoTurningDefaults {
        public static final double kP = 6e-5;
        public static final double kI = .000001;
        public static final double kD = .000001;
        public static final double kIz = 0;
        public static final double kFF = 0.000015;
        public static final double kMaxOutput = 1;
        public static final double kMinOutput = -1;
        public static final double maxRPM = 5500;
    }
}
