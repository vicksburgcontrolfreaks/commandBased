// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
  private final CANSparkMax collector = new CANSparkMax(5, MotorType.kBrushed);

  /** Creates a new Winch. */
  public void collectorMove(double speed){
    collector.set(speed);
  }

}
