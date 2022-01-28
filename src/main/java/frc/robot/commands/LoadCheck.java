// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;

public class LoadCheck extends CommandBase {
  private final Indexer m_indexer;
  private final Shooter m_shooter;
  private final MrMills m_mrMills;
  /** Creates a new IndexCheck. */
  public LoadCheck(Indexer sIndexer, Shooter sShooter, MrMills sMrMills) {
    m_mrMills = sMrMills;
    m_indexer = sIndexer;
    m_shooter = sShooter;
    addRequirements(m_indexer);
    addRequirements(m_mrMills);
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_mrMills.isIndexed() && m_shooter.shooterPrimed()){
      m_indexer.runIndexer(TestConstants.loadF);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.runIndexer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!m_mrMills.isIndexed())
      return true;
    else
      return false;
  }
}
