// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.MechTrain;

public class DriveSide extends CommandBase {
  /** Creates a new DriveSide. This code drives the robot either directly left or right a set distance
   *  Do to "build issues" the robot will follow a curved path and be imprecise
   * I have also not kept this code up to date very well so if any core methods of checking distances and such are different from DriveMech, do what is in DriveMech
  */
  private final MechTrain m_drive;
  private final Double m_dist;
  private final Double m_speed;
  double frontLeftF;
  double frontRightF;
  double backLeftF;
  double backRightF;
  public DriveSide(MechTrain subsystem, double x, double s) {
    //establishes all of the subsystems being called
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
    //same issue as driveMech
    double ticDist = m_dist*DriveConstants.ticksPerSide;
    frontLeftF = m_drive.frontLeftEncoderV() - ticDist;
    frontRightF = m_drive.frontRightEncoderV() + ticDist;
    backLeftF = m_drive.backLeftEncoderV() + ticDist;
    backRightF = m_drive.backRightEncoderV() - ticDist;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //sets the motors until the endpoint is reached
    m_drive.driveFrontLeft(frontLeftF, m_speed, 0);
    m_drive.driveFrontRight(frontRightF, m_speed, 0);
    m_drive.driveBackLeft(backLeftF, m_speed, 0);
    m_drive.driveBackRight(backRightF, m_speed, 0);


    testFinish();
    //SmartDashboard.putNumber("fLOff", frontLeftF - m_drive.frontLeftEncoderV());
    //SmartDashboard.putNumber("fROff", frontRightF - m_drive.frontRightEncoderV());
    //SmartDashboard.putNumber("bLOff", backLeftF - m_drive.backLeftEncoderV());
    //SmartDashboard.putNumber("bROff", backRightF - m_drive.backRightEncoderV());
    //SmartDashboard.putBoolean("testFinish", testFinish());

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
    //SmartDashboard.putNumber("FLAbsolute", absFL);
    //SmartDashboard.putNumber("BLAbsolute", absBL);
    //SmartDashboard.putNumber("FRAbsolute", absFR);
    //SmartDashboard.putNumber("BRAbsolute", absBR);


    double drive_encoderError = DriveConstants.drive_encoderError;
    boolean drivePostionReached = true;
    //SmartDashboard.putBoolean("check0", drivePostionReached);
    if ( absFL > drive_encoderError)
    drivePostionReached = false;
    //SmartDashboard.putBoolean("check1", drivePostionReached);
    if (absBL > drive_encoderError)
    drivePostionReached = false;
    //SmartDashboard.putBoolean("check3", drivePostionReached);    
    if (absBR > drive_encoderError)
    drivePostionReached = false;
    //SmartDashboard.putBoolean("check4", drivePostionReached);
    if (absFR > drive_encoderError)
    drivePostionReached = false;
    //SmartDashboard.putBoolean("check2", drivePostionReached);

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