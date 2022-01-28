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
import frc.robot.Constants.DriveConstants;

public class MechTrain extends SubsystemBase {
  private final CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax frontRight = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax backLeft = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);
  private final SparkMaxPIDController frontLeftP = frontLeft.getPIDController();
  private final SparkMaxPIDController frontRightP = frontRight.getPIDController();
  private final SparkMaxPIDController backLeftP = backLeft.getPIDController();
  private final SparkMaxPIDController backRightP = backRight.getPIDController();
  private final RelativeEncoder frontLeftE = frontLeft.getEncoder();
  private final RelativeEncoder frontRightE = frontRight.getEncoder();
  private final RelativeEncoder backLeftE = backLeft.getEncoder();
  private final RelativeEncoder backRightE = backRight.getEncoder();

  private final MecanumDrive mechDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  /** Creates a new MechTrain. */
  public void mecanumDrive(double x, double y){
    mechDrive.driveCartesian(y, x, 0);
  }

  public void quickTurn(double rot){
    mechDrive.driveCartesian(0, 0, rot);
  }

  public void setFrontLeftPids(int slot, double kMaxOutput, double kMinOutput){
    //establishes the PID values for each of the drive motors
    frontLeftP.setP(DriveConstants.drive_kP, slot);
    frontLeftP.setI(DriveConstants.drive_kI, slot);
    frontLeftP.setD(DriveConstants.drive_kD, slot);
    frontLeftP.setIZone(DriveConstants.drive_kIz, slot);
    frontLeftP.setFF(DriveConstants.drive_kFF, slot);
    frontLeftP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    frontLeftP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setFrontRightPids(int slot, double kMaxOutput, double kMinOutput){  
    frontRightP.setP(DriveConstants.drive_kP, slot);
    frontRightP.setI(DriveConstants.drive_kI, slot);
    frontRightP.setD(DriveConstants.drive_kD, slot);
    frontRightP.setIZone(DriveConstants.drive_kIz, slot);
    frontRightP.setFF(DriveConstants.drive_kFF, slot);
    frontRightP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    frontRightP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setBackLeftPids(int slot, double kMaxOutput, double kMinOutput){
    backLeftP.setP(DriveConstants.drive_kP, slot);
    backLeftP.setI(DriveConstants.drive_kI, slot);
    backLeftP.setD(DriveConstants.drive_kD, slot);
    backLeftP.setIZone(DriveConstants.drive_kIz, slot);
    backLeftP.setFF(DriveConstants.drive_kFF, slot);
    backLeftP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    backLeftP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setBackRightPids(int slot, double kMaxOutput, double kMinOutput){
    backRightP.setP(DriveConstants.drive_kP, slot);
    backRightP.setI(DriveConstants.drive_kI, slot);
    backRightP.setD(DriveConstants.drive_kD, slot);
    backRightP.setIZone(DriveConstants.drive_kIz, slot);
    backRightP.setFF(DriveConstants.drive_kFF, slot);
    backRightP.setSmartMotionAllowedClosedLoopError(DriveConstants.drive_encoderError, slot);
    backRightP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void driveFrontLeft(double distance, double speed, int slot){
    setFrontLeftPids(0, speed, -speed);
    frontLeftP.setReference(distance, ControlType.kPosition, 0);
  }
  
  public void driveFrontRight(double distance, double speed, int slot){
    setFrontRightPids(0, speed, -speed);
    frontRightP.setReference(distance, ControlType.kPosition, 0);
  }
  
  public void driveBackLeft(double distance, double speed, int slot){
    setBackLeftPids(0, speed, -speed);
    backLeftP.setReference(distance, ControlType.kPosition, 0);
  }
  
  public void driveBackRight(double distance, double speed, int slot){
    setBackRightPids(0, speed, -speed);
    backRightP.setReference(distance, ControlType.kPosition, 0);
  }

  @Override
  public void periodic() {
    frontLeftEncoderV();
    frontRightEncoderV();
    backLeftEncoderV();
    backRightEncoderV();
    avgV();
  }

  public double frontLeftEncoderV(){
    return frontLeftE.getPosition();
  }

  public double frontRightEncoderV(){
    return frontRightE.getPosition();
  }

  public double backLeftEncoderV(){
    return backLeftE.getPosition();
  }

  public double backRightEncoderV(){
    return backRightE.getPosition();
  }

  public double avgV(){
    double avg = (Math.abs(frontLeftE.getVelocity()) + Math.abs(frontRightE.getVelocity()) + Math.abs(backLeftE.getVelocity()) + Math.abs(backRightE.getVelocity()))/4;
    return avg;
  }
}
