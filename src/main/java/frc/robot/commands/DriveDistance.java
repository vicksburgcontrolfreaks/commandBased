// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.MechTrain;

public class DriveDistance extends CommandBase {
  /** Creates a new DriveDistance. This code drives the robot either directly forward or backward a set distance
   * 
   * Something is wrong with this code that causes it to turn instead of driving whenever an auton is run after having run another auton without power cycling
   * Good luck
  */
  private final MechTrain m_drive;
  private final Double m_dist;
  private final Double m_speed;
  double frontLeftF;
  double frontRightF;
  double backLeftF;
  double backRightF;
  public DriveDistance(MechTrain subsystem, double x, double s) {
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
    double ticDist = m_dist*DriveConstants.ticksPerInch;
    frontLeftF = m_drive.frontLeftEncoderV() + ticDist;
    frontRightF = m_drive.frontRightEncoderV() - ticDist;
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

    //Sends a bunch of test values to the smart dashboard.
    //This is fine for quick testing but values you want to use consistently should all be sent through 1 command.
    //See notes in DriveBug for more information.
    SmartDashboard.putNumber("frontLeftF", frontLeftF);
    SmartDashboard.putNumber("frontRightF", frontRightF);
    SmartDashboard.putNumber("backLeftF", backLeftF);
    SmartDashboard.putNumber("backRightF", backRightF);

    SmartDashboard.putNumber("frontRightEncoder", m_drive.frontRightEncoderV());
    SmartDashboard.putNumber("frontLeftEncoder", m_drive.frontLeftEncoderV());
    SmartDashboard.putNumber("backRightEncoder", m_drive.backRightEncoderV());
    SmartDashboard.putNumber("backLeftEncoder", m_drive.backLeftEncoderV());

    SmartDashboard.putNumber("fLOff", frontLeftF - m_drive.frontLeftEncoderV());
    SmartDashboard.putNumber("fROff", frontRightF - m_drive.frontRightEncoderV());
    SmartDashboard.putNumber("bLOff", backLeftF - m_drive.backLeftEncoderV());
    SmartDashboard.putNumber("bROff", backRightF - m_drive.backRightEncoderV());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //checks to see if the robot has driven to the endpoint. This could probably be seperated into a seperate function to make it read nicer.
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
}