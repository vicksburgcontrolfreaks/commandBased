// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.MechTrain;

public class SimpleAuton extends SequentialCommandGroup {
  /** Creates a new SimpleAuton. This is the autonomous made for testing short pieces of auton code */
  public SimpleAuton(MechTrain m_drive) {
    //adds each stage of our autonomous to a sequential group
    addCommands();
  }
}