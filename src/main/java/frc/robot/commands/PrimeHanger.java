// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PrimeHanger extends ParallelCommandGroup {
  /** Creates a new PrimeHanger. */
  public PrimeHanger(Turret m_turret, Lifter m_lifter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new PrimeTurret(m_turret),
    new LiftUp(m_lifter)
    );
  }

  public PrimeHanger(Turret m_Turret, Flopper m_Flopper) {
  }
}