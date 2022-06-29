// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimelightConstants;

public class Limelight extends SubsystemBase {
    /** Creates a new Limelight and names all of the tables from which values will be pulled*/
    //https://docs.limelightvision.io/en/latest/vision_pipeline_tuning.html
  NetworkTableInstance inst;
  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry tv;

  public Limelight (){
    //sets these values to tables in the Limelight upon initialization.
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");
  }

  @Override
  public void periodic() {
    //periodically checks the degrees off of the target in the x and y directions, the percent of the image that the target takes up, and whether or not a target is visible.
    tx();
    ty();
    ta();
    tv();
    fancyDistance();

    //sends values to the smart dashboard for the drivers. A better solution than this would likely be to send these values to the constants and then having a seperate command to print many values to the dashboard.
    SmartDashboard.putNumber("Distance", fancyDistance());
    SmartDashboard.putNumber("ConstantsDistance", LimelightConstants.currentDistance);
    SmartDashboard.putNumber("Shot Error", tx());
    SmartDashboard.putBoolean("Visible?", tv());

    //sends choice values to constants so that other parts of the code can access them without calling the Limelight.
    LimelightConstants.currentDistance = fancyDistance();
    LimelightConstants.visible = tv();
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
    //returns a boolean based on whether a target is visible or not. I feel like there is a better way to do this 
    if(tv.getDouble(0.0) == 1)
      return true;
    else 
      return false;
  }

  public double fancyDistance(){
    //I did not expect this to work and therefore gave it a poor name. This just calculates the distance between the lens of the limelight and a target based on some constants
    //Math is here https://docs.limelightvision.io/en/latest/cs_estimating_distance.html
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    double angleToGoalDegrees = LimelightConstants.mountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    //This would be better as multiplying by some constant instead of doing math with a bunch of decimals every time.

    double distanceFromLimelightToGoalInches = (LimelightConstants.goalHeightInches - LimelightConstants.heightInches)/Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }

  public boolean inRange(){
    //Checks  to see if the calculated distance is within the bounds of our shooters capabilities
    if(fancyDistance() >= LimelightConstants.lowRange && fancyDistance() <= LimelightConstants.highRange)
      return true;
    else
      return false;
  }

}