// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.DriveConstants;

public class MechTrain extends SubsystemBase {
  /** Creates a new MechTrain. This defines the mechanum based drive train of our robot*/
  //defines all of the Motors, Encoders, and PID Controllers that the drivetrain will use.
  private final CANSparkMax frontLeft = new CANSparkMax(CANConstants.frontLeft, MotorType.kBrushless);
  private final CANSparkMax frontRight = new CANSparkMax(CANConstants.frontRight, MotorType.kBrushless);
  private final CANSparkMax backLeft = new CANSparkMax(CANConstants.backLeft, MotorType.kBrushless);
  private final CANSparkMax backRight = new CANSparkMax(CANConstants.backRight, MotorType.kBrushless);
  private final SparkMaxPIDController frontLeftP = frontLeft.getPIDController();
  private final SparkMaxPIDController frontRightP = frontRight.getPIDController();
  private final SparkMaxPIDController backLeftP = backLeft.getPIDController();
  private final SparkMaxPIDController backRightP = backRight.getPIDController();
  private final RelativeEncoder frontLeftE = frontLeft.getEncoder();
  private final RelativeEncoder frontRightE = frontRight.getEncoder();
  private final RelativeEncoder backLeftE = backLeft.getEncoder();
  private final RelativeEncoder backRightE = backRight.getEncoder();

  //Defines our drive train as containing the previously established motors
  private final MecanumDrive mechDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  @Override
  public void periodic() {
    //repeatedly checks the position of each motor as well as the average speed of all drive motors
    frontLeftEncoderV();
    frontRightEncoderV();
    backLeftEncoderV();
    backRightEncoderV();
    avgV();
  }

  public void mecanumTurn(double x, double y, double rot){
    //drives the robot in mechanum style with rotation. This works best for controllers where the input for turning and translational movement are seperated
    frontLeft.setInverted(true);
    backLeft.setInverted(true);
    mechDrive.driveCartesian(y, -x, -rot);
  }

  //The standard mecanumDrive is best used in conjuntion with quickTurn on a controller with only 1 stick to avoid mixed inputs.
  public void mecanumDrive(double x, double y){
    //drives the robot in mechanum style without rotation
    mechDrive.driveCartesian(y, x, 0);
  }

  public void quickTurn(double rot){
    //only rotates the robot, allowing for turning without any chance of moving the whole robot
    mechDrive.driveCartesian(0, 0, rot);
  }

  //The values for all of the PID controllers should be identical to each other (probably) and can be determined using the characterization routine
  //Instructions here https://docs.wpilib.org/en/stable/docs/software/pathplanning/system-identification/introduction.html
  public void setFrontLeftPids(int slot, double kMaxOutput, double kMinOutput){
    //establishes the PID values for the front left drive motor
    frontLeftP.setP(DriveConstants.drive_kP, slot);
    frontLeftP.setI(DriveConstants.drive_kI, slot);
    frontLeftP.setD(DriveConstants.drive_kD, slot);
    frontLeftP.setIZone(DriveConstants.drive_kIz, slot);
    frontLeftP.setFF(DriveConstants.drive_kFF, slot);
    frontLeftP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    frontLeftP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setFrontRightPids(int slot, double kMaxOutput, double kMinOutput){ 
    //establishes the PID values for the front right drive motor 
    frontRightP.setP(DriveConstants.drive_kP, slot);
    frontRightP.setI(DriveConstants.drive_kI, slot);
    frontRightP.setD(DriveConstants.drive_kD, slot);
    frontRightP.setIZone(DriveConstants.drive_kIz, slot);
    frontRightP.setFF(DriveConstants.drive_kFF, slot);
    frontRightP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    frontRightP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setBackLeftPids(int slot, double kMaxOutput, double kMinOutput){
    //establishes the PID values for the back left drive motor 
    backLeftP.setP(DriveConstants.drive_kP, slot);
    backLeftP.setI(DriveConstants.drive_kI, slot);
    backLeftP.setD(DriveConstants.drive_kD, slot);
    backLeftP.setIZone(DriveConstants.drive_kIz, slot);
    backLeftP.setFF(DriveConstants.drive_kFF, slot);
    backLeftP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    backLeftP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setBackRightPids(int slot, double kMaxOutput, double kMinOutput){
    //establishes the PID values for the back right drive motor 
    backRightP.setP(DriveConstants.drive_kP, slot);
    backRightP.setI(DriveConstants.drive_kI, slot);
    backRightP.setD(DriveConstants.drive_kD, slot);
    backRightP.setIZone(DriveConstants.drive_kIz, slot);
    backRightP.setFF(DriveConstants.drive_kFF, slot);
    backRightP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    backRightP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void driveFrontLeft(double distance, double speed, int slot){
    //sets the front left motor to drive a specific distance within certain speed parmeters
    setFrontLeftPids(0, speed, -speed);
    frontLeftP.setReference(distance, ControlType.kPosition, 0);
  }
  
  public void driveFrontRight(double distance, double speed, int slot){
    //sets the front right motor to drive a specific distance within certain speed parmeters
    setFrontRightPids(0, speed, -speed);
    frontRightP.setReference(distance, ControlType.kPosition, 0);
  }
  
  public void driveBackLeft(double distance, double speed, int slot){
    //sets the back left motor to drive a specific distance within certain speed parmeters
    setBackLeftPids(0, speed, -speed);
    backLeftP.setReference(distance, ControlType.kPosition, 0);
  }
  
  public void driveBackRight(double distance, double speed, int slot){
    //sets the back right motor to drive a specific distance within certain speed parmeters
    setBackRightPids(0, speed, -speed);
    backRightP.setReference(distance, ControlType.kPosition, 0);
  }

  //These all check motor positions. V stands for value, not velocity. That was a bad name.
  public double frontLeftEncoderV(){
    //returns the current speed of the front left motor
    return frontLeftE.getPosition();
  }

  public double frontRightEncoderV(){
    //returns the current speed of the front left motor
    return frontRightE.getPosition();
  }

  public double backLeftEncoderV(){
    //returns the current speed of the back left motor
    return backLeftE.getPosition();
  }

  public double backRightEncoderV(){
    //returns the current speed of the back right motor
    return backRightE.getPosition();
  }

  public void resetEncoders(){
    //resets the drive encoders
    frontRightE.setPosition(0);
    frontLeftE.setPosition(0);
    backRightE.setPosition(0);
    backLeftE.setPosition(0);
  }

  public double avgV(){
    //returns the current average speed of all drive motors
    double avg = (Math.abs(frontLeftE.getVelocity()) + Math.abs(frontRightE.getVelocity()) + Math.abs(backLeftE.getVelocity()) + Math.abs(backRightE.getVelocity()))/4;
    return avg;
  }
}