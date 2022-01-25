// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Reacher;
import frc.robot.subsystems.Winch;

public class WinchRun extends CommandBase {
  public final Winch m_winch;
  private final Double m_speed;
  //public final Flopper m_flopper;
  //public final Reacher m_reacher;

  /** Creates a new WinchUp. */
  public WinchRun(Winch subsystem, double s) {
    m_winch = subsystem;
    m_speed = s;
    addRequirements(m_winch);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_winch.winchMove(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_winch.winchMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
