// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class NewAutoTurret extends CommandBase {
  // Creates a new AutoTurret. This is the default code that causes the turret to point towards the hub
  private final Turret m_turret;
  private final Limelight m_limelight;
  double turretPower;
  public NewAutoTurret(Turret subsystem, Limelight sLimelight) {
    //starts the turret in a mode to sweep to the left to find the target
    //establishes all of the subsystems being called
    m_turret = subsystem;
    m_limelight = sLimelight;
    addRequirements(m_turret, m_limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //turns the turret off and tells the turret to search in front of it for a target
    //the initial mode should probably be an input value so that it can be used differently in different circumstances.
    turretPower = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //calculates the distance that the turret needs to turn, in degrees, to be pointed towards the hub
    double distancePower;    
    double targetDistance = -m_limelight.tx();
    // double targetDistance = 5;
    double currentAngle = m_turret.turretEncoderP()/TurretConstants.ticksPerDegree;
    double targetDistanceAbs = Math.abs(targetDistance);
    double targetSign = Math.signum(targetDistance);
    //calculates the power that needs to be applied based on how far from the target the turret is 
    if(targetDistanceAbs > 2)
      distancePower = targetSign*(TurretConstants.farSpeed);
    else if(targetDistanceAbs >.5)
      distancePower = targetSign*(TurretConstants.closeSpeed);
    else
      distancePower = 0;
    //Switches the current mode of the turret based on specific actions
    //Mode = -1 tells the turret to sweep to the left, Mode = 1 tells the turret to sweep to the right, and Mode = 0 tells the turret to look for a target in its current vision.
    if((distancePower > 0 && currentAngle > TurretConstants.maxAngle) || (distancePower < 0 && currentAngle < TurretConstants.minAngle))
      m_turret.runTurret(0);
    else
      m_turret.runTurret(distancePower);
    
}
  


  


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // if other turret code is scheduled, this turns the turret off.
    if(interrupted)
      m_turret.runTurret(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //If the input was set such that this completes and the target is visible, this ends the command.
      return false;
  }
}