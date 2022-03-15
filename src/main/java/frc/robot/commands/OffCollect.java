// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;

public class OffCollect extends CommandBase {
  /** Creates a new CollectorRun. This code runs the collector at a specific speed*/
  private final Collector m_collector;
  private final Flopper m_flopper;

  public OffCollect(Collector subsystem, Flopper f) {
    m_collector = subsystem;
    m_flopper = f;
    addRequirements(m_collector);
    addRequirements(m_flopper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_flopper.flopIn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_collector.collectorMove(0);
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
    //this code runs continuously until it is interrupted by other turret code
    return false;
  }
}