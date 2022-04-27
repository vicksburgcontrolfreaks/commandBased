// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Lifter extends SubsystemBase {
  /** Creates a new Lifter. This mechanism moves the climbing mechanism from the down position to the climbing position*/
  private final Solenoid lifter = new Solenoid(PneumaticsModuleType.REVPH, CANConstants.liftSolenoid);
  public Lifter(){
    //moves the lifter to the up position when the code is intialized
    //THIS WAS A REMARKABLY TERRIBLE IDEA THAT REPEATEDLY ALMOST CAUSED INJURY (whoops)
    liftUp();
  }

  public void liftUp(){
    //moves both Lifters to the up position
    lifter.set(false);
  }

  public void liftDown(){
    //moves both Lifters to the down position
    lifter.set(true);
  }
}