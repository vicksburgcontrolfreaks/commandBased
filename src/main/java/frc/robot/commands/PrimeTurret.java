// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

//import edu.wpi.first.wpilibj.smartdashboard.//SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.Turret;

public class PrimeTurret extends CommandBase {
  private final Turret m_turret;
  double turretPower;
  double mode;
  double count;
  // Creates a new AutoTurret. This is the default code that causes the turret to point towards the hub
  public PrimeTurret(Turret subsystem) {

    mode = 1;
    m_turret = subsystem;
    addRequirements(m_turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turretPower = 0;
    count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    count++;
    //SmartDashboard.putNumber("count", count);
    ////SmartDashboard.putNumber("turretPower", turretPower);
    //calculates the distance that the turret needs to turn, in degrees, to be pointed towards the hub
    
    double currentAngle = m_turret.turretEncoderP()/TurretConstants.ticksPerDegree;
    double targetDistance = -currentAngle;
    // boolean rotateComplete = true;
    // boolean search = false;
    double targetDistanceAbs = Math.abs(targetDistance);
    double targetSign = targetDistance/targetDistanceAbs;
    double distancePower = targetSign*(.000000175*(targetDistanceAbs*targetDistanceAbs*targetDistanceAbs)-.0000711*(targetDistanceAbs*targetDistanceAbs)+.00992*targetDistanceAbs);
    double positionPower = .000000035*(currentAngle*currentAngle*currentAngle) + .00000651*(currentAngle*currentAngle) + .000622*currentAngle;
    turretPower = distancePower + positionPower;
    m_turret.runTurret(turretPower);
}
  


  


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.runTurret(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double currentAngle = m_turret.turretEncoderP()/TurretConstants.ticksPerDegree;
    //this code runs continuously until it is interrupted by other turret code
    if(Math.abs(currentAngle) < 4)
      return true;
    else 
      return false;
  }
}