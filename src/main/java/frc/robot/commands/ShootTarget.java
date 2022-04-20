// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class ShootTarget extends CommandBase {
  public final Shooter m_shooter;
  double targetSpeed;
  double count;
  /** Creates a new SetShooter. */
  public ShootTarget(Shooter s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = s;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    count++;
    if(LimelightConstants.visible)
      targetSpeed = m_shooter.distanceSpeed();
    else
      targetSpeed = ShooterConstants.shootF;
    m_shooter.shooterMove(targetSpeed);
    SmartDashboard.putBoolean("Shooter Primed", m_shooter.shooterPrimed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // m_shooter.shooterMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_shooter.shooterPrimed();
  }
}
