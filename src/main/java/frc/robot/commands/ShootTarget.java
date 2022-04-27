// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class ShootTarget extends CommandBase {
  public final Shooter m_shooter;
  double targetSpeed;
  /** Creates a new ShootTarget. This sets the shooter speed based on the distance from the hub*/
  public ShootTarget(Shooter s) {
    //establishes all of the subsystems being called
    m_shooter = s;
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //If the target is visible, sets the shooter to an appropriate speed. Otherwise, sets the shooter to full power
    if(LimelightConstants.visible)
      targetSpeed = m_shooter.distanceSpeed();
    else
      targetSpeed = ShooterConstants.shootF;
    m_shooter.shooterMove(targetSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //ends command once the shooter is primed
    return m_shooter.shooterPrimed();
  }
}
