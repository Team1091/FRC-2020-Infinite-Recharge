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
    public final class PWM {
        public static final int intakeMotor = 1;
        public static final int HopperMotor = 2;
        public static final int liftMotor = 3;
        public static final int hopperReleaseMotor = 4;
    }

    public final class CAN {
        public static final int leftDriveMotor = 1;
        public static final int rightDriveMotor = 2;
        public static final int firstShooterMotor = 3;
        public static final int secondShooterMotor = 4;
        public static final int aimMotor = 5;
    }

    public final class DIO {
        public static final int shooterLimitSwitch = 1;
        public static final int pickUpLimitSwitch = 2;
    }
    public final class RELAY {
        public static final int electroMag = 1;
    }
}
