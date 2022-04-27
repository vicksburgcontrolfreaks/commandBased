// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Lifter;

public class LiftUp extends InstantCommand {
  //Creates a new LiftUp. This moves the lifter to the up position. 
  public LiftUp(Lifter subsystem) {
    super(subsystem::liftUp, subsystem);
  }
}