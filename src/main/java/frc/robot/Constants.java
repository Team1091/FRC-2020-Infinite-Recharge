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
    public static final boolean hopperReleaseMotorInverted = true;
    public static final boolean rightDriveMotorInverted = false;
    public static final boolean leftDriveMotorInverted = false;
    public static final boolean aimMotorInverted = false;
    public static final boolean rightShooterMotorInverted = true;
    public static final boolean leftShooterMotorInverted = false;

    public static final double encoderToFootRatio = 4.16424275; // this many encoder ticks is 1 foot

    public static final class PWM {
        public static final int intakeMotor = 1;
        public static final int HopperMotor = 3;
        public static final int liftMotor = 0;
        public static final int hopperReleaseMotor = 2;
    }

    public static final class CAN {
        public static final int firstRightDriveMotor = 1;
        public static final int secondRightDriveMotor = 2;
        public static final int firstLeftDriveMotor = 3;
        public static final int secondLeftDriveMotor = 4;
        public static final int aimMotor = 5;
        public static final int leftShooterMotor = 6;
        public static final int rightShooterMotor = 7;
    }

    public static final class DIO {
        public static final int shooterLimitSwitch = 0;
        public static final int pickUpLimitSwitch = 1;
    }

    public static final class RELAY {
        public static final int electroMag = 0;
    }

    public static final class ShooterPositions {
        public static final int Pickup = 1;
        public static final int Shoot = 2;
    }

    public static final class SparkMaxNeoTurningDefaults {
        public static final double kP = 6e-5;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kIz = 0;
        public static final double kFF = 0.000015;
        public static final double kMaxOutput = 1;
        public static final double kMinOutput = -1;
        public static final double maxRPM = 5500;
    }
}
