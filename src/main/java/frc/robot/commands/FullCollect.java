// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;

public class FullCollect extends CommandBase {
  private final Collector m_collector;
  private final Double m_speed;
  private final Flopper m_flopper;

  public FullCollect(Collector subsystem, Flopper f, double s) {
    m_collector = subsystem;
    m_speed = s;
    m_flopper = f;
    addRequirements(m_collector);
    addRequirements(m_flopper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_flopper.flopOut();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_collector.collectorMove(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stop the collector when it is interrupted
    m_collector.collectorMove(0);
    m_flopper.flopIn();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other collector code
    return false;
  }
}