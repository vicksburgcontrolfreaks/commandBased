// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants{
        public final static double drive_kP = 2.81;
        public final static double drive_kI = 0;
        public final static double drive_kD = 0;
        public final static double drive_kIz = 0;
        public final static double drive_kFF = 0;
        public final static double drive_kMaxOutput = .3;
        public final static double drive_kMinOutput = -.3;
        public final static double ticksPerDegree = .23;
        public final static double ticksPerInch = .57;
        public final static double ticksPerSide = .76;
        public final static double drive_encoderError = 1;
    }

    public static final class TurretConstants{
        public final static double turret_kP = 2.81;
        public final static double turret_kI = 0;
        public final static double turret_kD = 0;
        public final static double turret_kIz = 0;
        public final static double turret_kFF = 0;
        public final static double turret_kMaxOutput = 1;
        public final static double ticksPerDegree = .23;
        public final static double turret_encoderError = 1;
    }

    public static final class TestConstants{
        public final static double collectF = .75;
        public final static double collectB = -1;
        public final static double shootF = 1;
        public final static double indexDist = 4;
        public final static double collectDistLow = 4;
        public final static double collectDistHigh = 12;
        public final static double shooterMin = 3000;
        public final static double indexF = .5;
        public final static double loadF = 1;
        public final static double maxAngle = 180 * TurretConstants.ticksPerDegree;
        public final static double winchF = 1;
        public final static double winchB = -.25;
    }
}

