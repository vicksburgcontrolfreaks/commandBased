// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.Turret;

public class ManualTurret extends CommandBase {
  // Creates a new ManualTurret. This runs the turret based on input from the secondary controller.
  private final Turret m_turret;
  private final DoubleSupplier m_speed;

  public ManualTurret(Turret subsystem, DoubleSupplier speed) {
    m_turret = subsystem;
    m_speed = speed;
    addRequirements(m_turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void execute() {
    double currentAngle = m_turret.turretEncoderP()/TurretConstants.ticksPerDegree;
    double positionPower = .000000035*(currentAngle*currentAngle*currentAngle) + .00000651*(currentAngle*currentAngle) + .000622*currentAngle;
    //runs the turret at a speed based on the x value of the controller
    m_turret.runTurret(-m_speed.getAsDouble() + positionPower);
  }

  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other turret code
    return false;
  }

}