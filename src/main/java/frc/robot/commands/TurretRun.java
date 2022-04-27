// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class TurretRun extends CommandBase {
  /** Creates a new TurretRun. This code runs the Turret at a specific speed*/
  public final Turret m_turret;
  private final Double m_speed;

  /** Creates a new CollectorRun. */
  public TurretRun(Turret subsystem, double s) {
    //establishes all of the subsystems being called
    m_turret = subsystem;
    m_speed = s;
    addRequirements(m_turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //sets the speed of the turret while this command is running
    m_turret.runTurret(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stop the turret when it is interrupted
    m_turret.runTurret(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other turret code
    return false;
  }
}
