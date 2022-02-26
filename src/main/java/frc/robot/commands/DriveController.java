// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.MechTrain;

public class DriveController extends CommandBase {
    //Creates a new DriveMech. This is the code that drives the robot based on controller inputs
  private final MechTrain m_drive;
  private final DoubleSupplier m_x;
  private final DoubleSupplier m_y;
  private final DoubleSupplier m_turn;

  public DriveController(MechTrain subsystem, DoubleSupplier x, DoubleSupplier y, DoubleSupplier turn) {
    m_drive = subsystem;
    m_x = x;
    m_y = y;
    m_turn = turn;
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void execute() {
    //sets the forward and sideways speeds of the robot based on controller inputs
    m_drive.mecanumTurn(DriveConstants.speedMultiplier*m_x.getAsDouble(), DriveConstants.speedMultiplier*m_y.getAsDouble(), DriveConstants.speedMultiplier*m_turn.getAsDouble());
  }
}