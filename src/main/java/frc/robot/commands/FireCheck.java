// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FireCheck extends SequentialCommandGroup {
  private final Indexer m_indexer;
  private final Collector m_collector;
  private final MrMills m_mrMills;
  private final Shooter m_shooter;
  private final Limelight m_limelight;
  /** Creates a new FireCheck. This fires two cargo while the shooter is at full speed.*/
  public FireCheck(Indexer sIndexer, Collector sCollector, MrMills sMrMills, Shooter sShooter, Limelight sLimelight) {
    m_indexer = sIndexer;
    m_mrMills = sMrMills;
    m_collector = sCollector;
    m_shooter = sShooter;
    m_limelight = sLimelight;
    addRequirements(m_indexer, m_mrMills, m_collector, m_shooter, m_limelight);
    //adds each stage of the firing process, running sequentially. This loads a cargo if one is indexed, then indexes and fires a second cargo
    addCommands(
      new LoadCheck(m_indexer, m_mrMills, m_shooter, m_limelight),
      new ParallelDeadlineGroup(new WaitCommand(.5), new CollectorRun(m_collector, TestConstants.collectF)),
      new IndexCheck(m_indexer, m_collector, m_mrMills), 
      new LoadCheck(m_indexer, m_mrMills, m_shooter, m_limelight),
      new WaitCommand(1),
      new ShooterRun(m_shooter, 0, false)
    );
  }
}