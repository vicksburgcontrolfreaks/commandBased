// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Reacher;
import frc.robot.subsystems.Winch;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class WinchUp extends ParallelCommandGroup {
  public final Winch m_winch;
  public final Reacher m_reacher;
  public final Flopper m_flopper;
  private final Double m_speed;
  // Creates a new WinchUp. This runs the winch to raise the robot and moves the pneumatics to the down position.
  public WinchUp(Winch subsystem, Flopper sFlopper, Reacher sReacher, double s) {
    m_winch = subsystem;
    m_speed = s;
    m_reacher = sReacher;
    m_flopper = sFlopper;
    addRequirements(m_winch);
    addRequirements(m_reacher);
    addRequirements(m_flopper);
    //runs the winch and the floppers at the same time
    addCommands(new FlopDown(m_flopper), new ReachDown(m_reacher), new WinchRun(m_winch, m_speed));
  }
}