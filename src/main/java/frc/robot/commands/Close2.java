// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class Close2 extends SequentialCommandGroup {
  /** Creates a new Autonomous1. This is the autonomous made for a starting position nearest to the side wall. */
  public Close2(MechTrain m_drive, Collector m_collector, Shooter m_shooter, Indexer m_indexer, MrMills m_mills, Turret m_turret, Limelight m_limelight) {
    //adds each stage of our autonomous to a sequential group
    addCommands(
      //drives the robot forward while running the collector. Both shut off when the distance has been driven.
      new ParallelDeadlineGroup(
        new DriveDistance(m_drive, 120, .6), 
        new CollectorRun(m_collector, TestConstants.collectF)),
      new DriveDistance(m_drive, 10, .6),
      //turns robot 90 degrees
      new TurnDegrees(m_drive, 180, .35),
      
      new DriveDistance(m_drive, 120, .6),
      new PrimingSequence(m_collector, m_indexer, m_mills, m_shooter, m_limelight),
      new FullFire(m_collector, m_indexer)

    );
  }
}