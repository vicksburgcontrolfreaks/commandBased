// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Flopper;

public class FlopOff extends InstantCommand {
  //Creates a new FlopOff. This turns the flopper off.
  public FlopOff(Flopper subsystem) {
    super(subsystem::flopOff, subsystem);
  }
}