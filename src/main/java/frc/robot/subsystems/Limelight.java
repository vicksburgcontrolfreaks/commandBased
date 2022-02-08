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
  public Limelight() {
  }

  @Override
  public void periodic() {
    tx();
    ty();
    ta();
    tv();
  }

  public double tx(){
    return tx.getDouble(0.0);
  }

  public double ty(){
    return ty.getDouble(0.0);
  }

  public double ta(){
    return ta.getDouble(0.0);
  }

  public boolean tv(){
    if(tv.getDouble(0.0) == 1)
      return true;
    else 
      return false;
  }

}