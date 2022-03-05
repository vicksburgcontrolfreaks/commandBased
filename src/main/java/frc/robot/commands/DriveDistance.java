// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.MechTrain;

public class DriveDistance extends CommandBase {
  /** Creates a new DriveDistance. This code drives the robot either directly forward or backward a set distance*/
  private final MechTrain m_drive;
  private final Double m_dist;
  private final Double m_speed;
  double count;
  double frontLeftF;
  double frontRightF;
  double backLeftF;
  double backRightF;
  public DriveDistance(MechTrain subsystem, double x, double s) {
    m_drive = subsystem;
    m_dist = x;
    m_speed = s;
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //calculates the distance each motor needs to travel in tics in order to drive the wanted distance
    m_drive.resetEncoders();
    double ticDist = -m_dist*DriveConstants.ticksPerInch;
    frontLeftF = m_drive.frontLeftEncoderV() + ticDist;
    frontRightF = m_drive.frontRightEncoderV() - ticDist;
    backLeftF = m_drive.backLeftEncoderV() + ticDist;
    backRightF = m_drive.backRightEncoderV() - ticDist;
    SmartDashboard.putNumber("initFLP", m_drive.frontLeftEncoderV());
    SmartDashboard.putNumber("initBLP", m_drive.backLeftEncoderV());
    SmartDashboard.putNumber("initFRP", m_drive.frontRightEncoderV());
    SmartDashboard.putNumber("initBRP", m_drive.backRightEncoderV());

    SmartDashboard.putNumber("initFL", frontLeftF);
    SmartDashboard.putNumber("initFR", frontRightF);
    SmartDashboard.putNumber("initBL", backLeftF);
    SmartDashboard.putNumber("initBR", backRightF);
    count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //sets the motors until the endpoint is reached
    m_drive.driveFrontLeft(frontLeftF, m_speed, 0);
    m_drive.driveFrontRight(frontRightF, m_speed, 0);
    m_drive.driveBackLeft(backLeftF, m_speed, 0);
    m_drive.driveBackRight(backRightF, m_speed, 0);
    SmartDashboard.putBoolean("isFinished", isFinished());
    SmartDashboard.putNumber("frontLeftF", frontLeftF);
    SmartDashboard.putNumber("frontRightF", frontRightF);
    SmartDashboard.putNumber("backLeftF", backLeftF);
    SmartDashboard.putNumber("backRightF", backRightF);

    testFinish();
    SmartDashboard.putNumber("fLOff", frontLeftF - m_drive.frontLeftEncoderV());
    SmartDashboard.putNumber("fROff", frontRightF - m_drive.frontRightEncoderV());
    SmartDashboard.putNumber("bLOff", backLeftF - m_drive.backLeftEncoderV());
    SmartDashboard.putNumber("bROff", backRightF - m_drive.backRightEncoderV());
    SmartDashboard.putBoolean("testFinish", testFinish());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //checks to see if the robot has driven to the endpoint
    double frontLeftEValue = m_drive.frontLeftEncoderV();
    double frontRightEValue = m_drive.frontLeftEncoderV();
    double backLeftEValue = m_drive.frontLeftEncoderV();
    double backRightEValue = m_drive.frontLeftEncoderV();
    SmartDashboard.putNumber("frontRightF", frontRightF);
    SmartDashboard.putNumber("frontRightEValue", frontRightEValue);
    SmartDashboard.putNumber("frontRightError", frontRightF+frontRightEValue);
    double drive_encoderError = DriveConstants.drive_encoderError;
    boolean drivePostionReached = true;
    SmartDashboard.putBoolean("check0", drivePostionReached);
    if (Math.abs(frontLeftF - frontLeftEValue) > drive_encoderError)
    drivePostionReached = false;
    SmartDashboard.putBoolean("check1", drivePostionReached);
    if (Math.abs(frontRightF + frontRightEValue) > drive_encoderError)
    drivePostionReached = false;
    SmartDashboard.putBoolean("check2", drivePostionReached);
    if (Math.abs(backLeftF - backLeftEValue) > drive_encoderError)
    drivePostionReached = false;
    SmartDashboard.putBoolean("check3", drivePostionReached);
    if (Math.abs(backRightF + backRightEValue) > drive_encoderError)
    drivePostionReached = false;
    SmartDashboard.putBoolean("check4", drivePostionReached);
    count++;
    SmartDashboard.putNumber("count", count);
    // if(m_drive.avgV() > DriveConstants.finalMotorV)
    // drivePostionReached = false;
    return drivePostionReached;

  }

  public boolean testFinish(){
        //checks to see if the robot has driven to the endpoint
        double frontLeftEValue = m_drive.frontLeftEncoderV();
        double frontRightEValue = m_drive.frontLeftEncoderV();
        double backLeftEValue = m_drive.frontLeftEncoderV();
        double backRightEValue = m_drive.frontLeftEncoderV();
        double drive_encoderError = DriveConstants.drive_encoderError;
        boolean drivePostionReached = true;
        if (Math.abs(frontLeftF - frontLeftEValue) > drive_encoderError)
        drivePostionReached = false;
        if (Math.abs(frontRightF - frontRightEValue) > drive_encoderError)
        drivePostionReached = false;
        if (Math.abs(backLeftF - backLeftEValue) > drive_encoderError)
        drivePostionReached = false;
        if (Math.abs(backRightF - backRightEValue) > drive_encoderError)
        drivePostionReached = false;
        // if(m_drive.avgV() > DriveConstants.finalMotorV)
        // drivePostionReached = false;
        return drivePostionReached;
  }
}