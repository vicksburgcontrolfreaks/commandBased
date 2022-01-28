// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TestConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class AutoTurret extends CommandBase {
  private final Turret m_turret;
  private final Limelight m_limelight;
  /** Creates a new DriveDistance. */
  public AutoTurret(Turret subsystem, Limelight sLimelight) {
    m_turret = subsystem;
    m_limelight = sLimelight;
    addRequirements(m_turret);
    addRequirements(m_limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ticDistance = m_limelight.tx()*TurretConstants.ticksPerDegree;
    double currentAngle = m_turret.turretEncoderP();
    double trueAngle = currentAngle + ticDistance;
    double targetAngle;
    double maxAngle = TestConstants.maxAngle;
    if(m_limelight.tv()){
      if(trueAngle > maxAngle || trueAngle < -maxAngle)
        targetAngle = currentAngle*-1 + ticDistance;
      else 
        targetAngle = trueAngle;
      m_turret.setTurret(targetAngle, TurretConstants.turret_kMaxOutput, 0);
    }
    else if (currentAngle > TestConstants.maxAngle || currentAngle < -TestConstants.maxAngle)
      m_turret.setTurret(-currentAngle, TurretConstants.turret_kMaxOutput, 0);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
