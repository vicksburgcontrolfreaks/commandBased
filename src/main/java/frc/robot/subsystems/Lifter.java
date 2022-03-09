// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Lifter extends SubsystemBase {
  /** Creates a new Lifter. Both cylinders are run from the same double solenoid*/
  private final Solenoid lifter = new DoubleSolenoid(PneumaticsModuleType.REVPH, CANConstants.liftSolenoid1, CANConstants.liftSolenoid2);

  public void liftUp(){
    //moves both Lifters to the up position
    lifter.set(Value.kForward);
  }

  public void liftDown(){
    //moves both Lifters to the down position
    lifter.set(Value.kReverse);
  }

  public void liftOff(){
    //turns both Lifters off. This code is unlikely to be used regularly but is necessary for our emergency stop.
    lifter.set(Value.kOff);
  }
}