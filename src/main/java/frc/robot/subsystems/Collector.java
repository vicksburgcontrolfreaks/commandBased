// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Collector extends SubsystemBase {
  /** Creates a new Collector. This is the motor that runs the multiple rows of collection wheels at the front of the robot. */
  private final CANSparkMax collector = new CANSparkMax(CANConstants.collector, MotorType.kBrushless);
  private final RelativeEncoder encoder = collector.getEncoder();

  @Override
  public void periodic() {
    //periodically checks the collector's current position
    collectorPosition();
  }

  public void collectorMove(double speed){
    //sets the speed of the collector to an input value
    collector.set(speed);
  }

  public double collectorPosition(){
    //returns the collector's current position
    return encoder.getPosition();
  }

}