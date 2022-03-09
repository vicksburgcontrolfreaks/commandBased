// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;

public class SetCollector extends CommandBase {
  private final Collector m_collector;
  private double totalDistance; //.6
  
  double currentPosition;
  double targetPosition;
  // Creates a new AutoTurret. This is the default code that causes the turret to point towards the hub
  public SetCollector(Collector subsystem, double dist) {
    m_collector = subsystem;
    dist = totalDistance;
    addRequirements(m_collector);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double negDist = -.6;
    targetPosition = -m_collector.collectorPosition() + negDist;
    // SmartDashboard.putNumber("intiPosit", m_collector.collectorPosition());
    // SmartDashboard.putNumber("initTarget", targetPosition);
    // SmartDashboard.putNumber("negDist", negDist);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //SmartDashboard.putNumber("turretPower", turretPower);
    //calculates the distance that the turret needs to turn, in degrees, to be pointed towards the hub
    currentPosition = -m_collector.collectorPosition();
    double distance = targetPosition - currentPosition;
    double absDistance = Math.abs(distance);
    double distSign = distance/absDistance;
    double distancePower = distSign*(-.15);
    //double distancePower = 0;
    m_collector.collectorMove(distancePower);
    //m_collector.collectorMove(0);
    // SmartDashboard.putNumber("current", currentPosition);
    // SmartDashboard.putNumber("targetPosition", targetPosition);
    // SmartDashboard.putNumber("distance", distance);
    // SmartDashboard.putNumber("distancePower", distancePower);
}
  


  


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_collector.collectorMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other turret code
    if(Math.abs(Math.abs(targetPosition)-Math.abs(currentPosition)) < TestConstants.collectorError)
      return true;
    else 
      return false;
  }
}