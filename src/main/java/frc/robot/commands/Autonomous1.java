// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;

public class Autonomous1 extends SequentialCommandGroup {
  /** Creates a new Autonomous1. This is the autonomous made for a starting position nearest to the side wall. */
  public Autonomous1(MechTrain m_drive, Collector m_collector, Shooter m_shooter, Indexer m_indexer, MrMills m_mills) {
    //adds each stage of our autonomous to a sequential group
    addCommands(
      //drives the robot 24 inches forward while running the collector. Both shut off when the distance has been driven.
      new ParallelDeadlineGroup(
        new DriveDistance(m_drive, 24, .25), 
        new CollectorRun(m_collector, TestConstants.collectF)),
      //turns robot 90 degrees
      new TurnDegrees(m_drive, 180, DriveConstants.drive_kMaxOutput),
      //drive the robot 24 inches to the side
      //new DriveSide(m_drive, 24, .25),
      //turns on the shooter and fires 2 cargo into the Upper Hub
      new ShooterRun(m_shooter, -1, true),
      new LoadCheck(m_indexer, m_shooter, m_mills)
    );
  }
}