// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;
public class DriveBug extends CommandBase {

/*This code was an unsuccessful attempt at putting all of the smart dasboard values in one place
  The issue is that it requires calling in a bunch of seperate subsystems, preventing these systems from being used elsewhere while sending values
  What should have been done is that all desired values would be sent to constants periodically and then called by this code and put into the smart dashboard
  Do that next time

  /** Creates a new Debug. */
 private final Limelight m_limelight;
 private final MrMills m_mills;
 private final Shooter m_shooter;
 boolean bigRedButton;
  public DriveBug(Limelight l, MrMills m, Shooter s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelight = l;
    m_mills = m;
    m_shooter = s;

    addRequirements(m_mills);
    addRequirements(m_limelight);
    addRequirements(m_shooter);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
