// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AdjusterConstants;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Adjuster;
import frc.robot.subsystems.Collector;

public class SetAdjuster extends CommandBase {
  private final Adjuster m_adjuster;
  private final Double degree;
  double currentPosition;
  double targetPosition;
  double distance;
  // Creates a new SetCollector. This approximately moves the collector a set distance
  public SetAdjuster(Adjuster subsystem, double d) {
    //establishes all of the subsystems being called
    m_adjuster = subsystem;
    degree = d;
    addRequirements(m_adjuster);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Sets the target position for the collector when the command is scheduled
    targetPosition = degree;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //calculates the distance that the Collector needs to turn, in tics, to be move the disired amount
    currentPosition = m_adjuster.adjusterDegrees();
    distance = targetPosition - currentPosition;
    double adjusterSpeed;
    if(Math.abs(distance) < TestConstants.adjusterCloseDist)
      adjusterSpeed = TestConstants.adjusterSpeedClose;
    else
      adjusterSpeed = TestConstants.adjusterSpeedFar;
    double distSign = Math.signum(distance);
    double distancePower = distSign*(adjusterSpeed);
    if((distance>0 && currentPosition < AdjusterConstants.maxAngle) || ((distance<0 && currentPosition > AdjusterConstants.minAngle)))
      m_adjuster.runAdjuster(distancePower);
    else
      m_adjuster.runAdjuster(0);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stops the collector when the command ends
    m_adjuster.runAdjuster(0);;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //Ends the command when the collector is within a certain amount of error of the target position
    if(Math.abs(distance) < .25)
      return true;
    else
      return false;
  }
}