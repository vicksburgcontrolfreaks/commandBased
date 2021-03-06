// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FullFire extends ParallelCommandGroup {
  //Creates a new FullFire. This runs the collector and the indexer in order to quickly run all cargo into the shooter
  public FullFire(Collector m_collector, Indexer m_indexer) {
    addCommands(
      new CollectorRun(m_collector, TestConstants.collectF),
      new IndexerRun(m_indexer, TestConstants.loadF)
    );
  }
}
