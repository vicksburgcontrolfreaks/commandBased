// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TestConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class TestTurret extends CommandBase {
  private final Turret m_turret;
  private final Limelight m_limelight;
  double turretPower;
  double mode;
  double count;
  // Creates a new AutoTurret. This is the default code that causes the turret to point towards the hub
  public TestTurret(Turret subsystem, Limelight sLimelight) {
    mode = 1;
    m_turret = subsystem;
    m_limelight = sLimelight;
    addRequirements(m_turret);
    addRequirements(m_limelight);

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
    SmartDashboard.putNumber("count", count);
    //SmartDashboard.putNumber("turretPower", turretPower);
    //calculates the distance that the turret needs to turn, in degrees, to be pointed towards the hub
    
    double targetDistance = -m_limelight.tx();
    double currentAngle = m_turret.turretEncoderP()/TurretConstants.ticksPerDegree;
    // boolean rotateComplete = true;
    // boolean search = false;
    double targetDistanceAbs = Math.abs(targetDistance);
    double targetSign = targetDistance/targetDistanceAbs;
    double distancePower = targetSign*(.000000175*(targetDistanceAbs*targetDistanceAbs*targetDistanceAbs)-.0000711*(targetDistanceAbs*targetDistanceAbs)+.00992*targetDistanceAbs);
    double positionPower = .000000035*(currentAngle*currentAngle*currentAngle) + .00000651*(currentAngle*currentAngle) + .000622*currentAngle;
    
    SmartDashboard.putNumber("PositionPower", positionPower);
    SmartDashboard.putNumber("CurrentAngle", currentAngle);
    SmartDashboard.putNumber("TargetDistance", targetDistance);
    SmartDashboard.putNumber("DistancePower", distancePower);
    SmartDashboard.putNumber("TargetAngle", targetDistanceAbs);
    SmartDashboard.putNumber("TurretPower", turretPower);
    SmartDashboard.putNumber("TargetSign", targetSign);
    SmartDashboard.putString("Limited?", "No");
    SmartDashboard.putNumber("Mode", mode);

    SmartDashboard.putNumber("limeX", m_limelight.tx());
    SmartDashboard.putBoolean("limeV", m_limelight.tv());
    //double trueAngle = -180;

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
        //double distancePower = targetSign*(1250.7*Math.log(targetDistanceAbs+4.35*1) - 261000000);
        turretPower = distancePower + positionPower;
        }
      //if the target is not visible and the turret has moved past the maximum angle, runs the turret all the way back to the other side
      else{
        //turretPower = m_turret.turretEncoderV();
        turretPower = positionPower;
      }
    }
    else if (mode == 1)
      turretPower = .15 + positionPower;
    else
      turretPower = -.15 + positionPower;
    m_turret.runTurret(turretPower);
    

    //if the limelight is visible, it rotates towards the target, taking the long way around if the angle is too great for the amount of slack in the wires
  //   if (search == true){
  //     //turretPower = m_turret.turretEncoderV();
  //     turretPower = -positionPower / positionPower * .25 + distancePower;
      
  // }
    
  //   else if(m_limelight.tv() && rotateComplete == true){
  //     targetAngle = trueAngle;
  //     double targetDistance = targetAngle - currentAngle;
  //     double targetDistanceAbs = Math.abs(targetAngle - currentAngle);
  //     double targetSign = targetDistance/targetDistanceAbs;
  //     //double distancePower = targetSign*(1250.7*Math.log(targetDistanceAbs+4.35*1) - 261000000);
  //     distancePower = targetSign*(.000000175*(targetDistanceAbs*targetDistanceAbs*targetDistanceAbs)-.0000711*(targetDistanceAbs*targetDistanceAbs)+.00992*targetDistanceAbs);
  //     positionPower = .000000035*(currentAngle*currentAngle*currentAngle) + .00000651*(currentAngle*currentAngle) + .000622*currentAngle;
  //     SmartDashboard.putNumber("PositionPower", positionPower);
  //     SmartDashboard.putNumber("CurrentAngle", currentAngle);
  //     SmartDashboard.putNumber("TargetDistance", targetDistance);
  //     SmartDashboard.putNumber("DistancePower", distancePower);
  //     SmartDashboard.putNumber("TargetAngle", targetAngle);
  //     SmartDashboard.putNumber("TargetAngle", targetDistanceAbs);
  //     SmartDashboard.putNumber("TurretPower", turretPower);
  //     SmartDashboard.putNumber("TargetSign", targetSign);
  //     SmartDashboard.putString("Limited?", "No");
  //     turretPower = distancePower + positionPower;
  //     }
  //   //if the target is not visible and the turret has moved past the maximum angle, runs the turret all the way back to the other side
  //   else{
  //     //turretPower = m_turret.turretEncoderV();
  //     turretPower = 0;
  //   m_turret.runTurret(turretPower);
  // }
  // if(trueAngle > TurretConstants.maxAngle || trueAngle < TurretConstants.minAngle){
  //   search = !search;
  //   rotateComplete = !rotateComplete;
  //     // targetAngle = currentAngle*-1 + ticDistance;
  //   }
}
  


  


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted)
      m_turret.runTurret(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other turret code

    return false;
  }
}