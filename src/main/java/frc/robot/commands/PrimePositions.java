// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MrMills;

public class PrimePositions extends SequentialCommandGroup {
  /** Creates a new Autonomous1. This is the autonomous made for a starting position nearest to the side wall. */
  public PrimePositions(Collector m_collector, Indexer m_indexer, MrMills m_mills) {
    //adds each stage of our autonomous to a sequential group
    addCommands(
      new SetCollector(m_collector, .8),
      new BackDexCheck(m_indexer, m_collector, m_mills)
    );
  }
}