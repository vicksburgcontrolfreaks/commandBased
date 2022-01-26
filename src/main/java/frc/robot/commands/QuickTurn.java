// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MechTrain;

public class QuickTurn extends CommandBase {
  private final MechTrain m_drive;
  private final DoubleSupplier m_rot;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public QuickTurn(MechTrain subsystem, DoubleSupplier rot) {
    m_drive = subsystem;
    m_rot = rot;
    addRequirements(m_drive);
  }

  @Override
  public void execute() {
    m_drive.quickTurn(m_rot.getAsDouble());
  }
}
