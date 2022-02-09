// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Reacher;

public class ReachUp extends InstantCommand {
  //creates a new ReachUp. This moves the reacher to the up position.
  public ReachUp(Reacher subsystem) {
    super(subsystem::reachUp, subsystem);
  }
}