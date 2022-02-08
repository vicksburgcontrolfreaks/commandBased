// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");
  /** Creates a new Limelight. */
  @Override
  public void periodic() {
    //periodically checks the degrees off of the target in the x and y directions, the percent of the image that the target takes up, and whether or not a target is visible.
    tx();
    ty();
    ta();
    tv();
  }

  public double tx(){
    //returns the number of degrees off of the target in the x direction
    return tx.getDouble(0.0);
  }

  public double ty(){
    //returns the number of degrees off of the target in the y direction
    return ty.getDouble(0.0);
  }

  public double ta(){
    //returns the percent of the limelights view that the area of the target takes up
    return ta.getDouble(0.0);
  }

  public boolean tv(){
    //returns a boolean based on whether a target is visible or not
    if(tv.getDouble(0.0) == 1)
      return true;
    else 
      return false;
  }

}