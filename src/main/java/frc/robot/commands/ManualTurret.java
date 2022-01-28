// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class ManualTurret extends CommandBase {
  private final Turret m_turret;
  private final DoubleSupplier m_speed;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public ManualTurret(Turret subsystem, DoubleSupplier speed) {
    m_turret = subsystem;
    m_speed = speed;
    addRequirements(m_turret);
  }

  @Override
  public void execute() {
    m_turret.runTurret(m_speed.getAsDouble());
  }

  public boolean isFinished() {
    return false;
  }
  
}
