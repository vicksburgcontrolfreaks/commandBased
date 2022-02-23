// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Reacher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Winch;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Stop extends ParallelCommandGroup {
  /** Creates a new Stop. This stops all of the subsystems from moving.*/
  public Stop(Shooter m_shooter, Collector m_collector, Winch m_winch, Indexer m_indexer, Flopper m_flopper, Reacher m_reacher, Turret m_turret) {
    addCommands(
      new ShooterRun(m_shooter, 0),
      new CollectorRun(m_collector, 0),
      new WinchRun(m_winch, 0),
      new IndexerRun(m_indexer, 0),
      new FlopOff(m_flopper),
      new TurretRun(m_turret, 0)
    );
  }
}
