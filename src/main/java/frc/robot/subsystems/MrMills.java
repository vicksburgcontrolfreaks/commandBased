// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TestConstants;

public class MrMills extends SubsystemBase {
  Rev2mDistanceSensor mrMills = new Rev2mDistanceSensor(com.revrobotics.Rev2mDistanceSensor.Port.kMXP);
  /** Creates a new MrMills. */
  public MrMills() {
    mrMills.setRangeProfile(RangeProfile.kHighSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    dist();
    isCollected();
    isIndexed();
  }

  public double dist(){
    return mrMills.getRange(Unit.kInches);
  }

  public boolean isIndexed(){
    if(dist() <= TestConstants.indexDist)
      return true;
    else 
      return false;
  }

  public boolean isCollected(){
    if(dist() <= TestConstants.collectDistHigh && dist() > TestConstants.collectDistLow)
      return true;
    else 
      return false;
  }
}
