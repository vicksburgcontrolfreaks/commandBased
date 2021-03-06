// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Indexer;
// import frc.robot.subsystems.Lifter;
// import frc.robot.subsystems.Reacher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Winch;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

//This is supposed to stop all motors when a botton is pressed but it actually does very little

public class Stop extends ParallelCommandGroup {
  /** Creates a new Stop. This stops all of the subsystems from moving.*/
  public Stop(Shooter m_shooter, Collector m_collector, Winch m_winch, Indexer m_indexer, Turret m_turret, Flopper m_flopper) {
    addCommands(
      // new ShooterRun(m_shooter, 0, false),
      // new CollectorRun(m_collector, 0),
      // new WinchRun(m_winch, m_turret, 0),
      // new FlopIn(m_flopper),
      // new IndexerRun(m_indexer, 0),
      // new TurretRun(m_turret, 0)
    );
  }
}
