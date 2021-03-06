// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PrimingSequence extends ParallelCommandGroup {
  /** Creates a new PrimingSequence. This starts running the shooter and moves the cargo into position.*/
  public PrimingSequence(Collector m_collector, Indexer m_indexer, MrMills m_mills, Shooter m_shooter) {
    addCommands(
      new ShooterRun(m_shooter, m_shooter.distanceSpeed(), false),
      new PrimePositions(m_collector, m_indexer, m_mills)
    );
  }
}
