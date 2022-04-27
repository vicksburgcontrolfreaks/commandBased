// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;

public class SetCollector extends CommandBase {
  private final Collector m_collector;
  
  double currentPosition;
  double targetPosition;
  // Creates a new SetCollector. This approximately moves the collector a set distance
  public SetCollector(Collector subsystem) {
    //establishes all of the subsystems being called
    m_collector = subsystem;
    addRequirements(m_collector);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Sets the target position for the collector when the command is scheduled
    double negDist = TestConstants.primeCollectorDist;
    targetPosition = -m_collector.collectorPosition() + negDist;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //calculates the distance that the Collector needs to turn, in tics, to be move the disired amount
    currentPosition = -m_collector.collectorPosition();
    double distance = targetPosition - currentPosition;
    double absDistance = Math.abs(distance);
    double distSign = distance/absDistance;
    double distancePower = distSign*(TestConstants.primeCollectorSpeed);
    m_collector.collectorMove(distancePower);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stops the collector when the command ends
    m_collector.collectorMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //Ends the command when the collector is within a certain amount of error of the target position
    if(Math.abs(Math.abs(targetPosition)-Math.abs(currentPosition)) < TestConstants.collectorError)
      return true;
    else 
      return false;
  }
}