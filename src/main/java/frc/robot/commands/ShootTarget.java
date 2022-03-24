// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class ShootTarget extends CommandBase {
  public final Shooter m_shooter;
  public final Limelight m_limelight;
  double targetSpeed;
  /** Creates a new SetShooter. */
  public ShootTarget(Shooter s, Limelight l) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = s;
    m_limelight = l;
    addRequirements(m_shooter, m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.tv())
      targetSpeed = m_shooter.distanceSpeed(m_limelight.fancyDistance());
    m_shooter.setSpeed(targetSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shooterMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
