// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
//import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.Shooter;

public class SimpleAuton extends SequentialCommandGroup {
  /** Creates a new Autonomous1. This is the autonomous made for a starting position nearest to the side wall. */
  public SimpleAuton(MechTrain m_drive) {
    //adds each stage of our autonomous to a sequential group
    addCommands(
      //drives the robot 24 inches forward while running the collector. Both shut off when the distance has been driven.
        //new DriveDistance(m_drive, 30, .25),
        // new DriveDistance(m_drive, 0, 0),
        new DriveDistance(m_drive, 0, .15)
        //new DriveDistance(m_drive, 30, .15)
    );
  }
}