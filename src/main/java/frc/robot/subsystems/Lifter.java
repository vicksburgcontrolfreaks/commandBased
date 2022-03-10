// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Lifter extends SubsystemBase {
  /** Creates a new Lifter. Both cylinders are run from the same double solenoid*/
  private final Solenoid lifter = new Solenoid(PneumaticsModuleType.REVPH, CANConstants.liftSolenoid);

  public void liftUp(){
    //moves both Lifters to the up position
    lifter.set(true);
  }

  public void liftDown(){
    //moves both Lifters to the down position
    lifter.set(false);
  }
}