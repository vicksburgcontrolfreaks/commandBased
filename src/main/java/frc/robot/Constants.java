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

    public static final class ClimberConstants{
        public final static int climberDirection = 1;
        public final static double manualUpSpeed = 1*climberDirection;
        public final static double manualDownSpeed = -1*climberDirection;
        public final static double autoUpSpeed = .25*climberDirection;
        public final static double autoDistance = 400;
    }




    public static final class DriveConstants{
        //establishes the constants used for driving precise distances
        public final static double turnSpeedMultiplier = .8;
        public final static double driveSpeedMultiplier = 1;
        public final static double drive_kP = 602;
        public final static double drive_kI = 0;
        public final static double drive_kD = 21.3;
        public final static double drive_kIz = 0;
        public final static double drive_kFF = 0;
        public final static double drive_kMaxOutput = .25;
        public final static double drive_kMinOutput = -.25;
        public final static double ticksPerDegree = .22;
        public final static double ticksPerInch = .57;
        public final static double ticksPerSide = .76;
        public final static double drive_encoderError = .5;
    }

    public static final class TurretConstants{
        //establishes the constants used for rotating the turret precise distances
        public final static double ticksPerDegree = .0156;
        public final static double minAngle = -90;
        public final static double maxAngle = 90;
        public final static double searchPower = .15;
    }

    public static final class ShooterConstants{
        public final static double shoot_kP = .0000002893;
        public final static double shoot_kI = 0;
        public final static double shoot_kD = 0;
        public final static double shoot_kIz = 0;
        public final static double shoot_kFF = 0;
        public final static double shoot_kMaxOutput = 1;
        public final static double shoot_kMinOutput = -1;
        public final static double shoot_encoderError = .5;
        public final static double shootF = -.9;
        public final static double speedMultiplier = 5176;
        public static double targetSpeed;
        public static double minSpeed = targetSpeed-200;
    }

    public static final class LimelightConstants{
        public final static double error = 4;
        public final static double mountAngleDegrees = 42.5;
        public final static double heightInches = 24;
        public final static double goalHeightInches = 102.625;
        public final static double lowRange = 84;
        public final static double highRange = 212;
        public static double currentDistance;
        public static boolean visible;
    }

    public static final class TestConstants{
        //a collection of the various values that will be changed through testing
        public final static double collectF = -.49;
        public final static double collectB = .25;
        public final static double overIndex = 2;
        public final static double indexDist = 8;
        public final static double collectDistHigh = 12;
        public final static double indexF = .15;
        public final static double indexB = -.1;
        public final static double loadF = 1;
        public final static double winchF = 1;
        public final static double winchB = -.25;
        public final static double collectorError = .1;
        public final static double winchDistance = 60;
        public final static double winchError = .5;
        public final static double primeCollectorSpeed = -.15;
        public final static double primeCollectorDist = -.6;
    }

    public static final class ShootButtons{
        //all of the buttons assigned to the shoot stick
        public final static int index = 1;
        public final static int backDex = 2;
        public final static int collectOn = 3;
        public final static int simpleCollect = 4;
        public final static int collectOff = 5;
        public final static int backCollect = 5;
        public final static int prime = 7;
        public final static int shooterOff = 8;
        public final static int manual = 9;
        public final static int auto = 10;
        public final static int primeHang = 11;
        public final static int unprimeHang = 12;
    }

    public static final class ControllerButtons{
        public final static int fire = 6; //RB
        public final static int pressFire = 5; //LB
        public final static int stop = 8; //START
        public final static int winchUp = 4; //Y
        public final static int winchDown = 1; //A
        public final static int winchAutoUp = 3; //X
        public final static int winchReset = 7; //BACK
    }

    public static final class CANConstants{
        //establishes the CAN IDs for each of the motors attached to a CAN Spark Max
        public final static int frontRight = 1;
        public final static int frontLeft = 2;
        public final static int backRight = 3;
        public final static int backLeft = 4;
        public final static int collector = 5;
        public final static int shooterL = 6;
        public final static int shooterR = 7;
        public final static int turret = 8;
        public final static int indexer = 13;
        public final static int winchL = 10;
        public final static int winchR = 11;
        public final static int liftSolenoid = 1;
        public final static int flopSolenoid = 2;
    }
}