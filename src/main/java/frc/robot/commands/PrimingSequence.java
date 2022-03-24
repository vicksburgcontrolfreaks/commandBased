// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PrimingSequence extends ParallelCommandGroup {
  /** Creates a new PrimingSequence. */
  public PrimingSequence(Collector m_collector, Indexer m_indexer, MrMills m_mills, Shooter m_shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // new SetShooter(m_shooter, ShooterConstants.targetSpeed),
      new ShooterRun(m_shooter, ShooterConstants.shootF, false),
      new PrimePositions(m_collector, m_indexer, m_mills)
    );
  }
}
