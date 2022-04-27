// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;


public class ShooterRun extends CommandBase {
  //Creates a new ShooterRun. This runs the shooter at a specific speed.
  public final Shooter m_shooter;
  private final Double m_speed;
  
  public ShooterRun(Shooter subsystem, double s, boolean completes) {
    //establishes all of the subsystems being called
    m_shooter = subsystem;
    m_speed = s;
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //runs the shooter at a set speed while the command is running
    m_shooter.shooterMove(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //This code runs once and then stops
    return true;
  }
}