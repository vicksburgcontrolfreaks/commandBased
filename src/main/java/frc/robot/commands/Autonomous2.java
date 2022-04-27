// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class Autonomous2 extends SequentialCommandGroup {
  /** Creates a new Autonomous1. This is the autonomous made for a starting position nearest to the side wall. */
  public Autonomous2(MechTrain m_drive, Collector m_collector, Shooter m_shooter, Indexer m_indexer, MrMills m_mills, Turret m_turret, Limelight m_limelight, Flopper m_flopper, Lifter m_lifter) {
    //adds each stage of our autonomous to a sequential group
    addCommands(
      //drives the robot forward while running the collector. Both shut off when the distance has been driven.
      new ParallelDeadlineGroup(
        new DriveDistance(m_drive, 44, .25), 
        new FullCollect(m_collector, m_flopper, TestConstants.collectF)),
      //drives the robot backwards slightly to clear the  wall while still running the collector to ensure that the cargo is fully controlled
      new ParallelDeadlineGroup(
        new DriveDistance(m_drive, -9, .25),  
        new FullCollect(m_collector, m_flopper, TestConstants.collectF)),
      //turns around to shoot
      new TurnDegrees(m_drive, 180, .3),
      //primes the shooter
      new PrimingSequence(m_collector, m_indexer, m_mills, m_shooter),
      //gives the turret 1.5 seconds to aim and moves the lifter out of the way.
      new ParallelDeadlineGroup(
        new WaitCommand(1.5), 
        new LiftDown(m_lifter),
        new AutoTurret(m_turret, m_limelight, false)),
      //fires loaded cargo into the Upper Hub
      new FireCheck(m_indexer, m_collector, m_mills, m_shooter)
    );
  }
}