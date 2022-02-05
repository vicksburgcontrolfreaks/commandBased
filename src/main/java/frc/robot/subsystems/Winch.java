// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Winch extends SubsystemBase {
  private final CANSparkMax winch = new CANSparkMax(CANConstants.winch, MotorType.kBrushed);

  /** Creates a new Winch. */
  public void winchMove(double speed){
    winch.set(speed);
  }

}
