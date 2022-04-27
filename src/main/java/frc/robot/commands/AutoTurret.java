// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class AutoTurret extends CommandBase {
  // Creates a new AutoTurret. This is the default code that causes the turret to point towards the hub
  private final Turret m_turret;
  private final Limelight m_limelight;
  private final boolean completes;
  double turretPower;
  double mode;
  public AutoTurret(Turret subsystem, Limelight sLimelight, boolean sComp) {
    //starts the turret in a mode to sweep to the left to find the target
    mode = 1;
    //establishes all of the subsystems being called
    completes = sComp;
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
    mode = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //calculates the distance that the turret needs to turn, in degrees, to be pointed towards the hub    
    double targetDistance = -m_limelight.tx();
    double currentAngle = m_turret.turretEncoderP()/TurretConstants.ticksPerDegree;
    double targetDistanceAbs = Math.abs(targetDistance);
    double targetSign = Math.signum(targetDistance);
    //calculates the power that needs to be applied based on how far from the target the turret is 
    double distancePower = targetSign*(.000000175*(targetDistanceAbs*targetDistanceAbs*targetDistanceAbs)-.0000711*(targetDistanceAbs*targetDistanceAbs)+.00992*targetDistanceAbs);
    //calculates the power that is needed to overcome the force of the wire management system
    double positionPower = .000000035*(currentAngle*currentAngle*currentAngle) + .00000651*(currentAngle*currentAngle) + .000622*currentAngle;

    //Switches the current mode of the turret based on specific actions
    //Mode = -1 tells the turret to sweep to the left, Mode = 1 tells the turret to sweep to the right, and Mode = 0 tells the turret to look for a target in its current vision.
    if(currentAngle > TurretConstants.maxAngle )
      mode = -1;
    else if(currentAngle < TurretConstants.minAngle)
      mode = 1;
    else if(mode == -1 && currentAngle < 0 && m_limelight.tv())
      mode = 0;
    else if(mode == 1 && currentAngle >= 0 && m_limelight.tv())
      mode = 0;

    if (mode == 0){
      if(m_limelight.tv()){
        turretPower = distancePower + positionPower;
        }
      else{
        turretPower = positionPower;
      }
    }
    else if (mode == 1)
      turretPower = TurretConstants.searchPower + positionPower;
    else
      turretPower = -TurretConstants.searchPower + positionPower;
    m_turret.runTurret(turretPower);
    
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
    //I don't know why I wrote this like that, this is a bad idea.
    if(completes && m_limelight.tv())
      return true;
    else
      return false;
  }
}