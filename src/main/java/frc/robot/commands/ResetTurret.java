// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Turret;

public class ResetTurret extends InstantCommand {
  //Creates a new ResetWinch. This sets the winch encoders to 0.
  public ResetTurret(Turret subsystem) {
    super(subsystem::turretReset, subsystem);
  }
}