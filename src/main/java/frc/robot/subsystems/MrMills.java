// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;





import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TestConstants;



public class MrMills extends SubsystemBase {
  double testCounter;
  /** Creates a new MrMills. This is the distance sensor used to determine the status of collected cargo, named after a local engineering teacher*/
  Rev2mDistanceSensor mrMills;

  public MrMills() {
    //establishes what port the sensor is on and tells it to start getting values when the robot is initialized.
    //The second line of code here is extremely important and this will not work if it is not present
    mrMills = new Rev2mDistanceSensor(Port.kOnboard, Unit.kInches, RangeProfile.kHighSpeed);
    mrMills.setAutomaticMode(true);
  }

  @Override
  public void periodic() {
    // Periodically checks the current distance between the sensor and an obsticle as well as using this information to determine where a ball is in the robot
    dist();
    isCollected();
    isIndexed();

    //Sends afformentioned values to the Smart Dashboard
    SmartDashboard.putBoolean("Indexed?", isIndexed());
    SmartDashboard.putBoolean("Over Indexed?", !isOverIndexed());
    SmartDashboard.putBoolean("Collected?", isCollected());
    SmartDashboard.putNumber("Dist", dist());
    }

  public double dist(){
    //returns the current distance between the sensor and an obstacle in inches
    return mrMills.getRange(Unit.kInches);
  }

  public boolean isIndexed(){
    //returns whether an object are close enough to be firmly indexed
    if(dist() <= TestConstants.indexDist)
      return true;
    else 
      return false;
  }

  public boolean isOverIndexed(){
    //returns whether an object is too far into the indexer and needs to be backed out before firing.
    if(dist() <= TestConstants.overIndex)
      return true;
    else 
      return false;
  }

  public boolean isCollected(){
    //returns whether an object is close enough to be collected but not so close that it is indexed
    //This value has some inconsistency as if there is no cargo at all, the sensor reads the wheel distance instead.
    if(dist() <= TestConstants.collectDistHigh && dist() > TestConstants.indexDist)
      return true;
    else 
      return false;
  }
}