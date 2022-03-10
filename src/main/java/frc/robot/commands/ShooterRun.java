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
  private final boolean m_comp;


  public ShooterRun(Shooter subsystem, double s, boolean completes) {
    m_shooter = subsystem;
    m_speed = s;
    m_comp = completes;
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // SmartDashboard.putBoolean("Pirated", m_shooter.shooterPrimed());
    // SmartDashboard.putBoolean("isFinished", isFinished());
    // SmartDashboard.putString("Running?", "yes");

    m_shooter.shooterMove(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // SmartDashboard.putString("Running?", "no");
    //stops the shooter when this command is interrupted
    if(interrupted)
    m_shooter.shooterMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!m_comp)
      return false;
    else
      return m_shooter.shooterPrimed();
    //this code runs continuously until it is interrupted by other shooter code
  }
}