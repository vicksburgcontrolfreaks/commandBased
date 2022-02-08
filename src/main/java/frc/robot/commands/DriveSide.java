// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.MechTrain;

public class DriveSide extends CommandBase {
  private final MechTrain m_drive;
  private final Double m_dist;
  private final Double m_speed;
  double frontLeftF;
  double frontRightF;
  double backLeftF;
  double backRightF;
  /** Creates a new DriveSide. */
  public DriveSide(MechTrain subsystem, double x, double s) {
    m_drive = subsystem;
    m_dist = x;
    m_speed = s;
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double ticDist = m_dist*DriveConstants.ticksPerSide;
    frontLeftF = m_drive.frontLeftEncoderV() + ticDist;
    frontRightF = m_drive.frontRightEncoderV() + ticDist;
    backLeftF = m_drive.backLeftEncoderV() - ticDist;
    backRightF = m_drive.backRightEncoderV() - ticDist;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.driveFrontLeft(frontLeftF, m_speed, 0);
    m_drive.driveFrontRight(frontRightF, m_speed, 0);
    m_drive.driveBackLeft(backLeftF, m_speed, 0);
    m_drive.driveBackRight(backRightF, m_speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
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
    if(m_drive.avgV() > .5)
    drivePostionReached = false;
    return drivePostionReached;
  }
}