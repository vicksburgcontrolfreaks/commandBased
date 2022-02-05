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
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Autonomous1 extends SequentialCommandGroup {
  /** Creates a new Autonomous1. */
  public Autonomous1(MechTrain m_drive, Collector m_collector, Shooter m_shooter, Indexer m_indexer) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelDeadlineGroup(
        new DriveDistance(m_drive, 24, .25), 
        new CollectorRun(m_collector, TestConstants.collectF)),
      new TurnDegrees(m_drive, 90, DriveConstants.drive_kMaxOutput),
      new DriveSide(m_drive, 24, .25),
      new ParallelDeadlineGroup(
        new FireCheck(),
        new ShooterRun(m_shooter, 1)
      )

    );
  }
}
