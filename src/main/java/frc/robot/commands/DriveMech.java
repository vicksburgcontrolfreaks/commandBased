// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MechTrain;

public class DriveMech extends CommandBase {
  private final MechTrain m_drive;
  private final DoubleSupplier m_x;
  private final DoubleSupplier m_y;

  //Creates a new DriveMech.

  public DriveMech(MechTrain subsystem, DoubleSupplier x, DoubleSupplier y) {
    m_drive = subsystem;
    m_x = x;
    m_y = y;
    addRequirements(m_drive);
  }

  @Override
  public void execute() {
    m_drive.mecanumDrive(m_x.getAsDouble(), m_y.getAsDouble());
  }
}