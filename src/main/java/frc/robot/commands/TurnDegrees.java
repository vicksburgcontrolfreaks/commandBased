// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.MechTrain;

public class TurnDegrees extends CommandBase {
  private final MechTrain m_drive;
  private final Double m_dist;
  private final Double m_speed;
  double frontLeftF;
  double frontRightF;
  double backLeftF;
  double backRightF;
  /** Creates a new TurnDegrees. This code turns the robot either directly left or right a set number of degrees*/
  public TurnDegrees(MechTrain subsystem, double x, double s) {
    m_drive = subsystem;
    m_dist = x;
    m_speed = s;
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //calculates the distance each motor needs to travel in tics in order to rotate the wanted distance
    double ticDist = m_dist*DriveConstants.ticksPerDegree;
    frontLeftF = m_drive.frontLeftEncoderV() - ticDist;
    frontRightF = m_drive.frontRightEncoderV() + ticDist;
    backLeftF = m_drive.backLeftEncoderV() - ticDist;
    backRightF = m_drive.backRightEncoderV() + ticDist;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //sets the motors until the endpoint is reached

    m_drive.driveFrontLeft(frontLeftF, m_speed, 0);
    m_drive.driveFrontRight(frontRightF, m_speed, 0);
    m_drive.driveBackLeft(backLeftF, m_speed, 0);
    m_drive.driveBackRight(backRightF, m_speed, 0);

    SmartDashboard.putNumber("frontLeftF", frontLeftF);
    SmartDashboard.putNumber("frontRightF", frontRightF);
    SmartDashboard.putNumber("backLeftF", backLeftF);
    SmartDashboard.putNumber("backRightF", backRightF);
    SmartDashboard.putNumber("frontRightEncoder", m_drive.frontRightEncoderV());
    SmartDashboard.putNumber("frontLeftEncoder", m_drive.frontLeftEncoderV());
    SmartDashboard.putNumber("backRightEncoder", m_drive.backRightEncoderV());
    SmartDashboard.putNumber("backLeftEncoder", m_drive.backLeftEncoderV());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //checks to see if the robot has driven to the endpoint
    double frontLeftEValue = m_drive.frontLeftEncoderV();
    double frontRightEValue = m_drive.frontRightEncoderV();
    double backLeftEValue = m_drive.backLeftEncoderV();
    double backRightEValue = m_drive.backRightEncoderV();

    double absFL = Math.abs(Math.abs(frontLeftF) - Math.abs(frontLeftEValue));
    double absFR = Math.abs(Math.abs(frontRightF) - Math.abs(frontRightEValue));
    double absBL = Math.abs(Math.abs(backLeftF) - Math.abs(backLeftEValue));
    double absBR = Math.abs(Math.abs(backRightF) - Math.abs(backRightEValue));

    SmartDashboard.putNumber("FLAbsolute", absFL);
    SmartDashboard.putNumber("BLAbsolute", absBL);
    SmartDashboard.putNumber("FRAbsolute", absFR);
    SmartDashboard.putNumber("BRAbsolute", absBR);


    double drive_encoderError = DriveConstants.drive_encoderError;
    boolean drivePostionReached = true;
    if (absFL > drive_encoderError)
    drivePostionReached = false;
    if (absFR > drive_encoderError)
    drivePostionReached = false;
    if (absBL > drive_encoderError)
    drivePostionReached = false;
    if (absBR > drive_encoderError)
    drivePostionReached = false;
    return drivePostionReached;
  }
}