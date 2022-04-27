// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Winch;

public class SetWinch extends CommandBase {
  // Creates a new SetWinch. This runs both sides of the winch up or down independently.
  private final Winch m_winch;
  private double totalDistance;
  double lPosition;
  double rPosition;
  double targetPosition;
  double lError;
  double rError;

  public SetWinch(Winch subsystem, double dist) {
    //establishes all of the subsystems being called
    m_winch = subsystem;
    dist = totalDistance;
    addRequirements(m_winch);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //sets the target position that the winch motors are aiming for.
    targetPosition = totalDistance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //calculates the distance and direction that either side of the winch needs to move
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
    //sets the left side of the winch to a set speed based on how far from the target position it is
    if(lError < TestConstants.winchError)
      lDistancePower = lDistSign*(ClimberConstants.autoUpSpeed);
    else
      lDistancePower = 0;
    //sets the right side of the winch to a set speed based on how far from the target position it is
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
    //stops the winch once the command ends
    m_winch.winchMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //Ends the code if both sides of the winch are sufficiently close to their end points.
    if(lError < TestConstants.winchError && rError < TestConstants.winchError)
      return true;
    else 
      return false;
  }
}