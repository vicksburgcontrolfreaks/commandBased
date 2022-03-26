// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Winch;

public class SetWinch extends CommandBase {
  private final Winch m_winch;
  private double totalDistance; //.6
  
  double lPosition;
  double rPosition;
  double targetPosition;
  double lError;
  double rError;
  // Creates a new AutoTurret. This is the default code that causes the turret to point towards the hub
  public SetWinch(Winch subsystem, double dist) {
    m_winch = subsystem;
    dist = totalDistance;
    addRequirements(m_winch);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targetPosition = totalDistance;
    // SmartDashboard.putNumber("intiPosit", m_collector.collectorPosition());
    // SmartDashboard.putNumber("initTarget", targetPosition);
    // SmartDashboard.putNumber("negDist", negDist);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //SmartDashboard.putNumber("turretPower", turretPower);
    //calculates the distance that the turret needs to turn, in degrees, to be pointed towards the hub
    double lDistancePower;
    double rDistancePower;
    lError = Math.abs(Math.abs(targetPosition)-Math.abs(lPosition));
    rError = Math.abs(Math.abs(targetPosition)-Math.abs(rPosition));
    lPosition = m_winch.leftWinchP();
    rPosition = m_winch.rightWinchP();
    double lDistance = targetPosition - lPosition;
    double rDistance = targetPosition - rPosition;
    double absLDistance = Math.abs(lDistance);
    double absRDistance = Math.abs(rDistance);
    double lDistSign = lDistance/absLDistance;
    double rDistSign = rDistance/absRDistance;
    if(lError < TestConstants.winchError)
      lDistancePower = lDistSign*(ClimberConstants.autoUpSpeed);
    else
      lDistancePower = 0;

    if(rError < TestConstants.winchError)
      rDistancePower = rDistSign*(ClimberConstants.autoUpSpeed);
    else
      rDistancePower = 0;
    m_winch.lWinchMove(lDistancePower);
    m_winch.rWinchMove(rDistancePower);
}
  


  


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_winch.winchMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other turret code
    if(lError < TestConstants.winchError && rError < TestConstants.winchError)
      return true;
    else 
      return false;
  }
}