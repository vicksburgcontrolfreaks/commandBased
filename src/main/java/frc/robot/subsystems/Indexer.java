// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Indexer extends SubsystemBase {
  //creates a new Indexer. This is the motor that drives the indexing and loading wheels to move cargo from the collector to the shooter.
  private CANSparkMax indexer = new CANSparkMax(CANConstants.indexer, MotorType.kBrushless);

  public void runIndexer(double speed){
    //sets the speed of the indexer to an input value
    indexer.set(-speed);
  }
}