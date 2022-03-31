// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Winch;

public class ResetWinch extends InstantCommand {
  //Creates a new FlopDown. This moves the flopper to the down position.
  public ResetWinch(Winch subsystem) {
    super(subsystem::resetEncoders, subsystem);
  }
}