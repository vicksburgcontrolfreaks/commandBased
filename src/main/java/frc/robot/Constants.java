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
        //establishes the constants used for driving precise distances
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
        public final static double finalMotorV = .5;
    }

    public static final class TurretConstants{
        //establishes the constants used for rotating the turret precise distances
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
        //a collection of the various values that will be changed through testing
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


    public class ControllerMap {
   
        //Controllers
        public static final int DRIVER_PORT = 0;
        public static final int OPERATOR_PORT = 1;
            
        //XboxOne Joysticks
        public static final int LEFT_STICK_X = 0;
        public static final int LEFT_STICK_Y = 1;
        public static final int LEFT_TRIGGER = 2;
        public static final int RIGHT_TRIGGER = 3;
        public static final int RIGHT_STICK_X = 4;
        public static final int RIGHT_STICK_Y = 5;
        
        //XboxOne Buttons
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
    }
    public static final class DriveButtons{
        //all of the buttons assigned to the drive stick

        public final static int fire = 1;
        public final static int turn = 2;
        public final static int flop = 3;
        public final static int reach = 5;
        public final static int stop = 7;
        public final static int resetEncoders = 11;
        public final static int reverseWinch = 12;
    }


    
    public static final class ShootButtons{
        //all of the buttons assigned to the shoot stick
        public final static int index = 1;
        public final static int collect = 3;
        public final static int winch = 4;
        public final static int backCollect = 5;
        public final static int prime = 7;
        public final static int shooterOff = 8;
        public final static int manual = 9;
        public final static int auto = 10;
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
        public final static int indexer = 9;
        public final static int winch = 10;
        public final static int flopSolenoid = 0;
        public final static int reachSolenoid = 1;
    }
}