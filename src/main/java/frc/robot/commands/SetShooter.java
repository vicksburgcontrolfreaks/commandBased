// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class SetShooter extends CommandBase {
  public final Shooter m_shooter;
  public final double target;
  /** Creates a new SetShooter. This runs the shooter at a set speed*/
  public SetShooter(Shooter s, double t) {
    //establishes all of the subsystems being called
    m_shooter = s;
    target = t;
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Sets the spped of the shooter while the command is running
    m_shooter.setSpeed(ShooterConstants.targetSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stops the shooter once the command ends
    m_shooter.shooterMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //This command only ends if it is interrupted by other shooter code.
    return false;
  }
}
