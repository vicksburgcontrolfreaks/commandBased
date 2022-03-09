// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;





import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TestConstants;



public class MrMills extends SubsystemBase {
  double testCounter;
  /** Creates a new MrMills. This is the distance sensor used to determine the status of collected cargo, named after a local engineering teacher*/

  Rev2mDistanceSensor mrMills;
  public MrMills() {
    mrMills = new Rev2mDistanceSensor(Port.kOnboard, Unit.kInches, RangeProfile.kHighSpeed);
    mrMills.setAutomaticMode(true);
  }

  @Override
  public void periodic() {
    // Periodically checks the current distance between the sensor and an obsticle as well as using this information to determine where a ball is in the robot. Also sends these values to the smart Dashboard
    
    dist();
    isCollected();
    isIndexed();
    // SmartDashboard.putBoolean("Indexed?", isIndexed());
    // SmartDashboard.putBoolean("Collected?", isCollected());
    // SmartDashboard.putNumber("Dist", dist());
    // SmartDashboard.putNumber("test", testCounter);

    }

  public double dist(){
    //returns the current distance between the sensor and an obstacle in inches
    return mrMills.getRange(Unit.kInches);
  }

  public boolean isIndexed(){
    //returns whether an objects are close enough to be firmly indexed
    if(dist() <= TestConstants.indexDist)
      return true;
    else 
      return false;
  }

  public boolean isOverIndexed(){
    //returns whether an objects are close enough to be firmly indexed
    if(dist() <= TestConstants.overIndex)
      return true;
    else 
      return false;
  }

  public boolean isCollected(){
    //returns whether an object is close enough to be collected but not so close that it is indexed
    if(dist() <= TestConstants.collectDistHigh && dist() > TestConstants.collectDistLow)
      return true;
    else 
      return false;
  }
}