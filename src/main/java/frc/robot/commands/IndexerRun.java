// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerRun extends CommandBase {
  // Creates a new IndexerRun. This runs the indexer at a specific speed.
  public final Indexer m_indexer;
  private final Double m_speed;

  public IndexerRun(Indexer subsystem, double s) {
    //establishes all of the subsystems being called
    m_indexer = subsystem;
    m_speed = s;
    addRequirements(m_indexer);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Sets the indexer to the desired speed
    m_indexer.runIndexer(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stops the indexer when other Indexer code is run
    m_indexer.runIndexer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other turret code
    return false;
  }
}